package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.constant.Constants
import com.example.uia.constant.Constants.*
import com.example.uia.databinding.ActivityLoginScreenBinding
import com.example.uia.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class LoginScreen : AppCompatActivity() {
    private lateinit var binding : ActivityLoginScreenBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerHereButton.setOnClickListener {
            var intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            var userEmail = binding.userEmail.text.toString()
            var password = binding.userPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                Toast.makeText(applicationContext,"Invalid Email Address Format", Toast.LENGTH_SHORT).show()
                binding.userEmail.requestFocus()
            }
            else if (password.length < 8){
                Toast.makeText(applicationContext,"Password length minimum 8 characters", Toast.LENGTH_SHORT).show()
                binding.userPassword.requestFocus()
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext,"Logged in Successfully", Toast.LENGTH_SHORT).show()

                        sharedPreferences.edit().putString("email",userEmail).apply()
                        sharedPreferences.edit().putString("quiz_status","Done").apply()

                        getDataIntoSharedPreferences()

                        var intent = Intent(this,ResultScreen::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(applicationContext,"Login Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getDataIntoSharedPreferences() {

        FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().currentUser!!.uid).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (snapshot : DataSnapshot in snapshot.children){
                    if(Objects.equals(snapshot.key,"education"))
                        sharedPreferences.edit().putString("userEducation","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"gender"))
                        sharedPreferences.edit().putString("userGender","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"major"))
                        sharedPreferences.edit().putString("userMajor","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"married"))
                        sharedPreferences.edit().putString("userMaritial","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"name"))
                        sharedPreferences.edit().putString("name","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"no"))
                        sharedPreferences.edit().putString("number","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"profilePicture"))
                        sharedPreferences.edit().putString("profilePicture","" + snapshot.value).apply()
                    if(Objects.equals(snapshot.key,"email"))
                        sharedPreferences.edit().putString("email","" + snapshot.value).apply()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}