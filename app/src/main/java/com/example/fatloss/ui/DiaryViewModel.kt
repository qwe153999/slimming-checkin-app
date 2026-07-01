package com.example.fatloss.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fatloss.data.DiaryEntry
import com.example.fatloss.data.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DiaryRepository.create(application)

    private val _entries = MutableStateFlow<List<DiaryEntry>>(emptyList())
    val entries: StateFlow<List<DiaryEntry>> = _entries.asStateFlow()

    init {
        viewModelScope.launch {
            repository.entries().collect { list ->
                _entries.value = list
            }
        }
    }

    fun add(entry: DiaryEntry) = viewModelScope.launch {
        repository.insert(entry)
    }

    fun update(entry: DiaryEntry) = viewModelScope.launch {
        repository.update(entry)
    }

    fun delete(entry: DiaryEntry) = viewModelScope.launch {
        repository.delete(entry)
    }

    suspend fun load(id: Int): DiaryEntry? = repository.get(id)
}
