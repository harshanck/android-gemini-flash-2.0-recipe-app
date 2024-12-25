package com.kodagoda.airecipes.data.repository

import android.graphics.Bitmap
import com.kodagoda.airecipes.data.service.GenAIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenAIRepository(private val service: GenAIService) {
    suspend fun generate(prompt: String, bitmap: Bitmap): Flow<String> = flow {
        val content = service.generate(prompt, bitmap)
        emit(content)
    }
}