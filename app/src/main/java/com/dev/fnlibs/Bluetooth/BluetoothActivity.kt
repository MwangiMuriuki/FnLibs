package com.dev.fnlibs.Bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : AppCompatActivity() {
    val REQUEST_ENABLE_BT: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        btSearchButton.setOnClickListener {
            checkBluetoothEnabled()
        }
    }

    private fun checkBluetoothEnabled() {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(
                applicationContext,
                "This device does not support bluetooth. Please use a different device",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
        else
        {
            queryPairedDevices()
        }
    }

    private fun queryPairedDevices() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        val myDevices: MutableList<String> = ArrayList()
        pairedDevices?.forEach { device ->

            myDevices.add(device.getName())

            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address

            Log.d("DEVICES_TAG", deviceName)

            pairedDevicesReceyclerView.hasFixedSize()
            pairedDevices.size

        }
    }
}