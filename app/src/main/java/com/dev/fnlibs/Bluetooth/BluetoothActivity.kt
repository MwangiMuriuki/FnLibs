package com.dev.fnlibs.Bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : AppCompatActivity() {
    val REQUEST_ENABLE_BT: Int = 1000
    var myDevices = ArrayList<btDataClass>()
    val list = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        btPairedButton.setOnClickListener {
            checkBluetoothEnabled()
        }

        btSearchButton.setOnClickListener {
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
            else{
                scanForBTDevices()
            }
        }
    }

    private fun scanForBTDevices() {

        list.clear()

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

        myDevices = ArrayList()

        if (pairedDevices?.size!! > 0){
            pairedDevices.forEach {

                val deviceName = it.name
                val deviceHardwareAddress = it.address // MAC address

                list.add(deviceName+"\n"+deviceHardwareAddress)

                Log.d("DEVICES_TAG", pairedDevices.toString())
                val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
                    this, android.R.layout.simple_list_item_1, list as List<Any?>
                )

                pairedDevicesReceyclerView.adapter = arrayAdapter

                pairedDevicesReceyclerView.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
                    val selectedDevice: String = list.get(pos).toString()
                    Toast.makeText(this, selectedDevice,Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
