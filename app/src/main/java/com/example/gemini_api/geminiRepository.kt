package com.example.gemini_api

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel

class geminiRepository {


    private val TAG = "GeminiRepo"
    val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )


    suspend fun askGemini(userInput: String): String {
        Log.i(TAG, "askGemini() called with input: ${userInput.take(20)}...")
        // return at the top to account for both the cases at the same time
         try {

            val response = generativeModel.generateContent(userInput)

            // first case return statement
            val text = response.text ?: "Gemini returned an empty response."

            Log.d(TAG, "Success! Received ${text.length} characters from Gemini")

             return text

        } catch (e: Exception) {

            Log.e(TAG, "ERROR in askGemini() while processing input: $userInput", e)

            println("Error generating content: ${e.message}")
            // second case return statement
             val text = "Sorry, I couldn't reach the server. Error: ${e.localizedMessage}"

             return text
        }
    }
}