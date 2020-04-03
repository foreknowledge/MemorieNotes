package com.foreknowledge.memorynotes.framework.di

import android.app.Application
import com.foreknowledge.memorynotes.framework.RoomNoteDataSource
import com.foreknowledge.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}