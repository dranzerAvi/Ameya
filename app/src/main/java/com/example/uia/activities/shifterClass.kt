package com.example.uia.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.R
import com.example.uia.constant.Constants
import com.example.uia.databinding.ActivityShifterClassBinding


class shifterClass : AppCompatActivity() {
    lateinit var binding : ActivityShifterClassBinding
    var SPLASH_SCREEN_TIME_OUT = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShifterClassBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var value = intent.getIntExtra("num",-1)

        Handler().postDelayed({
            finish()
        }, SPLASH_SCREEN_TIME_OUT.toLong())

        when (value) {
            1 -> {
                binding.text.text = "Let's Go😎!! 4 more to Go"
                binding.lottieFile.setAnimation(R.raw.exa)
            }
            2 -> {
                binding.text.text = "You are on Correct Path🤝🏽!! 3 more to go"
                binding.lottieFile.setAnimation(R.raw.exb)
            }
            3 -> {
                binding.text.text = "Half Way There!!, You got this💪🏽"
                binding.lottieFile.setAnimation(R.raw.exc)
            }
            4 -> {
                binding.text.text = "Wonderful Job🤩, Take your Last Step"
                binding.lottieFile.setAnimation(R.raw.exd)
            }
            5 -> {
                binding.text.text = "Amazing!! Let's Look at Your Results"
                binding.lottieFile.setAnimation(R.raw.exe)
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }

    }
}