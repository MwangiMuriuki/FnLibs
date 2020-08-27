package com.dev.fnlibs.NotesWithRoomDB.fragments

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.dev.fnlibs.NotesWithRoomDB.Helpers.toast
import com.dev.fnlibs.NotesWithRoomDB.dataBase.Note
import com.dev.fnlibs.NotesWithRoomDB.dataBase.NoteDatabase
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class FragmentAddNote : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       fabSave.setOnClickListener { view: View ->
           val nTitle = noteTitle.text.toString().trim()
           val nBody = noteBody.text.toString().trim()

           if (nTitle.isEmpty()){
               noteTitle.error = "Enter Title"
               noteTitle.requestFocus()
               return@setOnClickListener
           }

           if (nBody.isEmpty()){
               noteBody.error = "Enter your note"
               noteBody.requestFocus()
               return@setOnClickListener
           }

           launch {
               val note = Note(nTitle,nBody)
               context?.let {
                   NoteDatabase(it).getNoteDao().addNote(note)
                   it.toast("Note Saved")
                   noteTitle.text = null
                   noteBody.text = null

                   val action = FragmentAddNoteDirections.actionSaveNote()
                   Navigation.findNavController(view).navigate(action)
               }
           }

//           saveNote(note)

       }
    }

//    private fun saveNote(note: Note){
//        class SaveNote : AsyncTask<Void, Void, Void>(){
//            override fun doInBackground(vararg p0: Void?): Void? {
//                NoteDatabase(requireActivity()).getNoteDao().addNote(note)
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//
//                Toast.makeText(activity, "Note Saved", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        SaveNote().execute()
//    }

}