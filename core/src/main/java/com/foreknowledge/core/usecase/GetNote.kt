package com.foreknowledge.core.usecase

import com.foreknowledge.core.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long) = repository.getNote(id)
}