package com.example.gemini_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class geminiViewModel : ViewModel() {

    private val repository = geminiRepository()
    private val _response = MutableStateFlow("Getting Your Response Ready")
    private val _prompt = MutableStateFlow("text")

    val prompt = _prompt.asStateFlow()
    val response = _response.asStateFlow()


    fun btnClicked(query: String) {
        viewModelScope.launch {
            _prompt.value = query
            _response.value = repository.askGemini(prompt.value)
        }
    }

}