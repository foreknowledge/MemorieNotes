package com.foreknowledge.cleanarchitectureex.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.foreknowledge.cleanarchitectureex.R
import com.foreknowledge.cleanarchitectureex.framework.NoteViewModel
import com.foreknowledge.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private val currentNote = Note("", "", 0L, 0L)

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
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(title.windowToken, 0)
    }

    private fun EditText.isNotBlank() = text.toString().isNotBlank()
}
