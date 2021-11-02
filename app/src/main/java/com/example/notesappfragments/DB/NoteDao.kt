package com.example.notesappfragments.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes ORDER BY Note DESC")
    fun getNotes(): LiveData<List<NoteDetail>>

    @Insert
    fun insertNote(noteDetail: NoteDetail)

    @Delete
    fun deleteNote(noteDetail: NoteDetail)

    @Query("UPDATE Notes SET Note=:n WHERE id=:id")
    fun updateNote(id:Int,n:String)

}