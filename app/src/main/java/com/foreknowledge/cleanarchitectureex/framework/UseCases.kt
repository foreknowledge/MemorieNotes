package com.foreknowledge.cleanarchitectureex.framework

import com.foreknowledge.core.usecase.AddNote
import com.foreknowledge.core.usecase.GetAllNotes
import com.foreknowledge.core.usecase.GetNote
import com.foreknowledge.core.usecase.RemoveNote

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)