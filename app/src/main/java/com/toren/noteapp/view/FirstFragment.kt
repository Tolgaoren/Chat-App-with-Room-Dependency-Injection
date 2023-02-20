package com.toren.noteapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.toren.noteapp.adapter.NotesAdapter
import com.toren.noteapp.databinding.FragmentFirstBinding
import com.toren.noteapp.model.Note
import com.toren.noteapp.viewmodel.FirstViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(),
    NotesAdapter.OnItemClickListener{

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by viewModels()
    private lateinit var note: Note
    private lateinit var noteAdapter: NotesAdapter
    private lateinit var notes: ArrayList<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes
        loadNotes()

        notes = ArrayList()
        noteAdapter = NotesAdapter(notes, this)

        binding.apply {

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = noteAdapter

            button.setOnClickListener {

                val text = textInput.editText!!.text.toString()
                if (text.isNotEmpty() && text.isNotBlank()) {
                    note = Note(id=0,
                        text)
                    viewModel.insertNote(note)
                    textInput.editText!!.text.clear()
                    viewModel.getNotes
                    loadNotes()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadNotes() {
        viewModel.getNotes.observe(viewLifecycleOwner) {
            it?.let{
                notes.clear()
                notes.addAll(it as ArrayList<Note>)
                noteAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClick(position: Int) {
        val note = notes[position]
        viewModel.deleteNote(note)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}