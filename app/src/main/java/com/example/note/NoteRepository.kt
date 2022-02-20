package com.example.note

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val getAllNotes : LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(note : NoteEntity){
        noteDao.insert(note)
    }

    suspend fun delete(note: NoteEntity)
    {
        noteDao.delete(note)
    }
}
