package com.example.uia.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.databinding.ActivityLoginScreenBinding
import com.example.uia.databinding.ActivityMentorRegisterBinding
import com.example.uia.databinding.MentorconnectLayoutBinding
import com.example.uia.models.Mentor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MentorRegister : AppCompatActivity() {

    private lateinit var binding : ActivityMentorRegisterBinding
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.mentorRegister.setOnClickListener {
            var name = binding.mentorName.text.toString()
            var email = binding.mentorEmail.text.toString()
            var linkedIn = binding.mentorLinkedIn.text.toString()
            var years = binding.mentorWorkExperience.text.toString()
            var workAs = binding.mentorWorkAs.text.toString()

            if (name.isEmpty() || email.isEmpty() || linkedIn.isEmpty() || years.isEmpty() || workAs.isEmpty())
                Toast.makeText(applicationContext,"Field Can't be Empty", Toast.LENGTH_SHORT).show()
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                Toast.makeText(applicationContext,"Invalid Email Format", Toast.LENGTH_SHORT).show()
            else if(!linkedIn.contains("https://www.linkedin.com/in/")) {
                binding.mentorLinkedIn.requestFocus()
                Toast.makeText(applicationContext, "Insert In the format : https://www.linkedin.com/in/", Toast.LENGTH_SHORT).show()
            }
            else{
                var databaseRef : DatabaseReference = FirebaseDatabase.getInstance().reference
                var uniqueID = databaseRef.push().key.toString()
                var linkedInName = linkedIn.substring(28)
                var mentorObj = Mentor(name,email,linkedInName,years,workAs)
                FirebaseDatabase.getInstance().getReference("Mentor").child(uniqueID).setValue(mentorObj)
                Toast.makeText(applicationContext,"Registration Complete", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}