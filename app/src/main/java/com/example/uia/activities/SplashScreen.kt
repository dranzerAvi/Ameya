package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.R
import com.example.uia.databinding.ActivityCommonQuestBinding
import com.example.uia.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding
    lateinit var handler : Handler
    private lateinit var preferences : SharedPreferences
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        preferences = getSharedPreferences("userData", MODE_PRIVATE)
        handler = Handler()

        handler.postDelayed({
            if (FirebaseAuth.getInstance().currentUser != null){
                val intent = Intent(this, ResultScreen::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
            }

            finish()
        },2000)

    }


}