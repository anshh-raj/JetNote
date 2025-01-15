package com.example.jetnote.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) :ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{ listOfNotes->
//                if(listOfNotes.isEmpty()){
//                    Log.d("Empty",": Empty List")
//                }
//                else{
                    _noteList.value = listOfNotes
//                }
            }
        }
    }


    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(newNote: Note, oldNote: Note) = viewModelScope.launch {

        val existingNote = getNoteById(oldNote)

        val updatedNote = existingNote.copy(
            title = newNote.title,
            description = newNote.description
        )
        repository.updateNote(updatedNote)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    private suspend fun getNoteById(note: Note) =
        repository.getNotesById(note)


//    private var noteList = mutableStateListOf<Note>()
//    init {
//        noteList.addAll(NoteDataSource().loadNotes())
//    }

//    fun addNote(note:Note){
//        noteList.add(0,note)
//    }
//
//    fun removeNote(note: Note){
//        noteList.remove(note)
//    }
//
//    fun editNote(oldNote: Note, newNote: Note){
//        noteList.remove(oldNote)
//        noteList.add(0,newNote)
//    }
//
//    fun getAllNotes(): List<Note>{
//        return noteList
//    }

}