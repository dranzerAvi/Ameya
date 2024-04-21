package com.example.uia.activities

import android.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.databinding.ActivityDeveloperProfileBinding

class Developer_Profile : AppCompatActivity() {

    lateinit var binding : ActivityDeveloperProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDeveloperProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.github.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Darshanbennur"))
            startActivity(browserIntent)
        }

        binding.linkedin.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/darshan-bennur/"))
            startActivity(browserIntent)
        }

        binding.instagram.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/darshan_what_a_drag/"))
            startActivity(browserIntent)
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.gmail.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(R.attr.label.toString(), "bennurdarshan@gmail.com")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext,"Email Copied", Toast.LENGTH_SHORT).show()
            val email = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, "bennurdarshan@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Collaboration")
                putExtra(Intent.EXTRA_TEXT, "Hi, I want to connect with you")
            }
            startActivity(email)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}