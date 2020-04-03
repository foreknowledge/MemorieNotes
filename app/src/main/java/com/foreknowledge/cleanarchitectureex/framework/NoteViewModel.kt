package com.foreknowledge.cleanarchitectureex.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.foreknowledge.core.data.Note
import com.foreknowledge.core.repository.NoteRepository
import com.foreknowledge.core.usecase.AddNote
import com.foreknowledge.core.usecase.GetAllNotes
import com.foreknowledge.core.usecase.GetNote
import com.foreknowledge.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            currentNote.postValue(useCases.getNote(id))
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
            saved.postValue(true)
        }
    }
}