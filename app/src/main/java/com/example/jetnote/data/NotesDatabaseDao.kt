package com.example.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NotesDatabaseDao {
    @Query("SELECT * FROM NOTES_TBL")
    fun getNotes(): Flow<List<Note>>  // no need of suspend flow will take care of it

    @Query("SELECT * FROM NOTES_TBL WHERE id=:noteId")
    suspend fun getNoteById(noteId:UUID):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    @Query("DELETE FROM NOTES_TBL")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
