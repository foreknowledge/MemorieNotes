package com.foreknowledge.core.usecase

import com.foreknowledge.core.data.Note
import com.foreknowledge.core.repository.NoteRepository

class AddNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}