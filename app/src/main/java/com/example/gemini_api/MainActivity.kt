package com.example.gemini_api

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gemini_api.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : geminiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.main)

        setupListeners()
        observeState()


    }

    private fun setupListeners(){
        binding.btn.setOnClickListener {
            viewModel.btnClicked(binding.tiPrompt.text.toString())
        }
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.response.collect { response ->
                binding.tvResponse.text = response
            }

        }

    }
}