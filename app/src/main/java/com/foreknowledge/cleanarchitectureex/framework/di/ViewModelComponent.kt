package com.foreknowledge.cleanarchitectureex.framework.di

import com.foreknowledge.cleanarchitectureex.framework.ListViewModel
import com.foreknowledge.cleanarchitectureex.framework.NoteViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}