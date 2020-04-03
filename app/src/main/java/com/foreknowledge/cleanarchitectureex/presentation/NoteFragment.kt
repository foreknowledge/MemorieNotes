package com.foreknowledge.cleanarchitectureex.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.foreknowledge.cleanarchitectureex.R
import com.foreknowledge.cleanarchitectureex.framework.NoteViewModel
import com.foreknowledge.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L)
            viewModel.getNote(noteId)

        storeNote.setOnClickListener {
            if (title.isNotBlank() || content.isNotBlank()) {
                val time = System.currentTimeMillis()

                currentNote.title = title.text.toString()
                currentNote.content = content.text.toString()
                currentNote.updateTime = time

                if (currentNote.id == 0L)
                    currentNote.creationTime = time

                viewModel.saveNote(currentNote)
            }
            else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(title).popBackStack()
            }
            else {
                Toast.makeText(context, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {note ->
            note?.let {
                currentNote = it
                title.setText(it.title, TextView.BufferType.EDITABLE)
                content.setText(currentNote.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(title.windowToken, 0)
    }

    private fun EditText.isNotBlank() = text.toString().isNotBlank()
}
