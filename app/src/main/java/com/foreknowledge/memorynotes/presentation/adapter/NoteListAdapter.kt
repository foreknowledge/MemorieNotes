package com.foreknowledge.memorynotes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.memorynotes.R
import com.foreknowledge.core.data.Note
import com.foreknowledge.memorynotes.presentation.listener.ItemClickListener
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(private var notes: List<Note>, private val listener: ItemClickListener): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val noteLayout = view.noteLayout
        private val noteTitle = view.title
        private val noteContent = view.content
        private val noteDate = view.date
        private val noteWords = view.wordCount

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteContent.text = note.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss", Locale.getDefault())
            val resultDate = "Last updated: ${sdf.format(Date(note.updateTime))}"
            noteDate.text = resultDate

            noteWords.text = "Words: ${note.wordCount}"
            noteLayout.setOnClickListener{ listener.onClick(note.id) }
        }
    }
}