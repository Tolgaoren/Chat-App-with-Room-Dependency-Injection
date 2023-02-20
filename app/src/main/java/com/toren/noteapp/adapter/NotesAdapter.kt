package com.toren.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toren.noteapp.databinding.NotesBinding
import com.toren.noteapp.model.Note
import com.toren.noteapp.view.FirstFragment

class NotesAdapter(
    private val notes: ArrayList<Note>,
    private val clickListener: FirstFragment
) : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    inner class NoteHolder(val binding: NotesBinding) :
            RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position : Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = NotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.binding.notesText.text = notes[position].noteText
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}