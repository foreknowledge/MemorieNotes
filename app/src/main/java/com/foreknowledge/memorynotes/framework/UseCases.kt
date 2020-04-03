package com.foreknowledge.memorynotes.framework

import com.foreknowledge.core.usecase.*

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)