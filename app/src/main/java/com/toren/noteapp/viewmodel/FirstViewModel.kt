package com.toren.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.toren.noteapp.model.Note
import com.toren.noteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel
    @Inject constructor(private val repository: Repository)
    : ViewModel() {

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }
    val getNotes = repository.getNotes().asLiveData()

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}