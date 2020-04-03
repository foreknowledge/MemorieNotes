package com.foreknowledge.cleanarchitectureex.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foreknowledge.core.data.Note

@Entity(tableName = "note")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_date")
    val updateTime: Long
) {
    companion object {
        fun fromNote(note: Note) =
            NoteEntity(note.id, note.title, note.content, note.creationTime, note.updateTime)
    }

    fun toNote() = Note(id, title, content, creationTime, updateTime)
}