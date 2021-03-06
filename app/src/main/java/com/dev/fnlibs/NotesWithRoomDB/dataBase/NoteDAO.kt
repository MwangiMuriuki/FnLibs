package com.dev.fnlibs.NotesWithRoomDB.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {

    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNotes() : List<Note>

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)
}