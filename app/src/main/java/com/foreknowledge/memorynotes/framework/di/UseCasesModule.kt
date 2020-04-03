package com.foreknowledge.memorynotes.framework.di

import com.foreknowledge.memorynotes.framework.UseCases
import com.foreknowledge.core.repository.NoteRepository
import com.foreknowledge.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}