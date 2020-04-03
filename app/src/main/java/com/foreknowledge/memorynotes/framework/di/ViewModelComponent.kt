package com.foreknowledge.memorynotes.framework.di

import com.foreknowledge.memorynotes.framework.ListViewModel
import com.foreknowledge.memorynotes.framework.NoteViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}