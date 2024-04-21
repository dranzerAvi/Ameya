package com.example.uia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import com.example.uia.R
import com.example.uia.databinding.ActivityResultBinding
import com.example.uia.databinding.ActivityWebviewBinding

class webview : AppCompatActivity() {
    lateinit var binding : ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityWebviewBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var link = intent.getStringExtra("url")

        binding.web.webViewClient = WebViewClient()
        binding.web.loadUrl(link.toString())
        binding.web.settings.javaScriptEnabled = true
        binding.web.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}