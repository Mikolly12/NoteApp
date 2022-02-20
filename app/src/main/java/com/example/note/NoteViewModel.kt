package com.example.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<NoteEntity>>
    private val repository: NoteRepository
    init{
        val dao = NoteRoomDataBase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)
        allNotes =repository.getAllNotes
    }

    fun deleteNote(note : NoteEntity) = viewModelScope.launch(Dispatchers.IO){
            repository.delete(note)
    }

    fun addNote(note : NoteEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}