package com.foreknowledge.memorynotes.framework

import android.content.Context
import com.foreknowledge.memorynotes.framework.db.DatabaseService
import com.foreknowledge.memorynotes.framework.db.NoteEntity
import com.foreknowledge.core.data.Note
import com.foreknowledge.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}