package com.dev.fnlibs.NotesWithRoomDB.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dev.fnlibs.NotesWithRoomDB.dataBase.Note
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.note_layout.view.*

class AdapterNotes(val context: Context, val notes: List<Note>) : RecyclerView.Adapter<AdapterNotes.NotesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.view.note_title.text = notes[position].title
        holder.view.note_body.text = notes[position].noteBody

        val message: String = notes[position].title

        holder.view.notesMainLayout.setOnClickListener {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}

