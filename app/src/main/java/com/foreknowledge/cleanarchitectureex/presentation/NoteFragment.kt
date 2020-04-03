package com.foreknowledge.cleanarchitectureex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.foreknowledge.cleanarchitectureex.R
import kotlinx.android.synthetic.main.fragment_note.*

/**
 * A simple [Fragment] subclass.
 */
class NoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeNote.setOnClickListener { Navigation.findNavController(it).popBackStack() }
    }

}
