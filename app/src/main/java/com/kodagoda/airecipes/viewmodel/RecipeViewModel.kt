package com.kodagoda.airecipes.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodagoda.airecipes.data.repository.GenAIRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: GenAIRepository) : ViewModel() {
    var content by mutableStateOf("")
    var isCapturing by mutableStateOf(true)
    var isLoading by mutableStateOf(false)

    fun gen(image: Bitmap) {
        viewModelScope.launch {
            val prompt = "Analyze this image and identify food items in it, " +
                    "then give a list of food items and a best possible recipe. If it is already done dish, " +
                    "give name and recipe with list of ingredients."
            repository.generate(prompt, image).collect { data ->
                content = data
                isLoading = false
            }
        }
    }
}