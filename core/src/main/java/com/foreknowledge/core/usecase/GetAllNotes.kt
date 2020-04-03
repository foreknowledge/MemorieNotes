package com.foreknowledge.core.usecase

import com.foreknowledge.core.repository.NoteRepository

class GetAllNotes(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getAllNotes()
}