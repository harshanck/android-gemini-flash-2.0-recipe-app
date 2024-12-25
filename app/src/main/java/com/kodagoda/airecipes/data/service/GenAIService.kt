package com.kodagoda.airecipes.data.service

import android.graphics.Bitmap
import com.google.firebase.vertexai.GenerativeModel
import com.google.firebase.vertexai.type.content

class GenAIService(private val model: GenerativeModel) {

    suspend fun generate(prompt: String, bitmap: Bitmap): String {
        return try {
            val content = content {
                image(bitmap)
                text(prompt)
            }
            val response = model.generateContent(content)
            response.text ?: "No response"
        } catch (e: Exception) {
            throw e
        }
    }
}