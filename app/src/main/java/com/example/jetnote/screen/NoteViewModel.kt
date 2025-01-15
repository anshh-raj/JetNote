package com.example.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.data.NoteDataSource
import com.example.jetnote.model.Note

class NoteViewModel:ViewModel() {

    private var noteList = mutableStateListOf<Note>()

//    init {
//        noteList.addAll(NoteDataSource().loadNotes())
//    }

    fun addNote(note:Note){
        noteList.add(0,note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun editNote(oldNote: Note, newNote: Note){
        noteList.remove(oldNote)
        noteList.add(0,newNote)
    }

    fun getAllNotes(): List<Note>{
        return noteList
    }
}