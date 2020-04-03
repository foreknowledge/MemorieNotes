package com.foreknowledge.cleanarchitectureex.framework.di

import com.foreknowledge.cleanarchitectureex.framework.UseCases
import com.foreknowledge.core.repository.NoteRepository
import com.foreknowledge.core.usecase.AddNote
import com.foreknowledge.core.usecase.GetAllNotes
import com.foreknowledge.core.usecase.GetNote
import com.foreknowledge.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )
}