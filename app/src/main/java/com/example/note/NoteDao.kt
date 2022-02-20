package com.example.note


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note : NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("Select * from note_table order by id ASC")
    fun getAllNotes() : LiveData<List<NoteEntity>>

}
