package com.foreknowledge.cleanarchitectureex.framework.di

import android.app.Application
import com.foreknowledge.cleanarchitectureex.framework.RoomNoteDataSource
import com.foreknowledge.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}