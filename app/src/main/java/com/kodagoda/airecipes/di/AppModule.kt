package com.kodagoda.airecipes.di

import com.google.firebase.Firebase
import com.google.firebase.vertexai.type.generationConfig
import com.google.firebase.vertexai.vertexAI
import com.kodagoda.airecipes.data.repository.GenAIRepository
import com.kodagoda.airecipes.data.service.GenAIService
import com.kodagoda.airecipes.viewmodel.RecipeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val config = generationConfig {
            temperature = 0.7f
        }
        Firebase.vertexAI.generativeModel(
            modelName = "gemini-2.0-flash-exp", //"gemini-1.5-flash": Gemini 2.0 gives more detailed results
            generationConfig = config
        )
    }
    single { GenAIService(get()) }
    single { GenAIRepository(get()) }
    viewModel { RecipeViewModel(get()) }
}