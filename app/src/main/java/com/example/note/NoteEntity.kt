package com.example.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class NoteEntity(@ColumnInfo(name ="text") var text: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}