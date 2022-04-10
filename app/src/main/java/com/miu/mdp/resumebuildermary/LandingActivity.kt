package com.miu.mdp.resumebuildermary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miu.mdp.resumebuildermary.databinding.ActivityLoginBinding

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = ""
    }
}
