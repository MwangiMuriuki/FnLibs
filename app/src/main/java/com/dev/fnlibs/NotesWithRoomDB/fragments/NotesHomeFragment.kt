package com.dev.fnlibs.NotesWithRoomDB.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev.fnlibs.NotesWithRoomDB.adapters.AdapterNotes
import com.dev.fnlibs.NotesWithRoomDB.dataBase.NoteDatabase
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.fragment_notes_home.*
import kotlinx.coroutines.launch


class NotesHomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notesRecyclerView.setHasFixedSize(true)
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(3,  StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                notesRecyclerView.adapter = AdapterNotes(it, notes)
            }
        }

        fabAdd.setOnClickListener {
            val action = NotesHomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }
    }

}