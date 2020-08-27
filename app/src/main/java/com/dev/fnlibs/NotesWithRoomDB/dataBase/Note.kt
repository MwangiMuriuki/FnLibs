package com.dev.fnlibs.NotesWithRoomDB.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val noteBody: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}