package com.example.uia.activities

import android.R.attr.label
import android.app.ProgressDialog
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.R
import com.example.uia.databinding.ActivityProfilePageBinding
import android.content.ClipboardManager
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class ProfilePage : AppCompatActivity() {

    lateinit var binding : ActivityProfilePageBinding
    private lateinit var sharedPreferences : SharedPreferences
    lateinit var uri : Uri
    var isProfilePictureSet : Boolean = false
    var isReUploadProfilePictureSet : Boolean = false
    lateinit var progressBar : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.userName.text = sharedPreferences.getString("name","Oppsies Error").toString()

        binding.changePhoto.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/+"
            startActivityForResult(intent,100)
        }

        binding.removePhoto.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.profile)
            isProfilePictureSet = false
            isReUploadProfilePictureSet = false
            sharedPreferences.edit().putString("profilePicture","notSet").apply()
        }

        var profileUrl = sharedPreferences.getString("profilePicture","notSet")
        if (profileUrl.equals("notSet")){
            binding.imageView.setImageResource(R.drawable.profile)
            isProfilePictureSet = false
        }else{
            try{
                Glide.with(this).load(profileUrl).into(binding.imageView)
                isReUploadProfilePictureSet = true
                isProfilePictureSet = true
            }catch (e : Exception){
                binding.imageView.setImageResource(R.drawable.profile)
                e.printStackTrace()
            }

        }

        binding.uploadImage.setOnClickListener {
            if (isReUploadProfilePictureSet){
                Toast.makeText(applicationContext,"Profile Is Already Set, Select Another Image to Change", Toast.LENGTH_SHORT).show()
            }else{
                if (isProfilePictureSet){
                    progressBar = ProgressDialog(this)
                    progressBar.setTitle("Uploading...")
                    progressBar.show()
                    FirebaseStorage.getInstance().getReference("images/" + UUID.randomUUID().toString()).putFile(uri).addOnCompleteListener { it1->
                        if (it1.isSuccessful){
                            it1.result.storage.downloadUrl.addOnCompleteListener {
                                if (it.isSuccessful){
                                    var url : String = it.result.toString()
                                    sharedPreferences.edit().putString("profilePicture",it.result.toString()).apply()
                                    FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().currentUser!!.uid).child("profilePicture").setValue(url)
                                    isReUploadProfilePictureSet = true
                                }
                            }
                            Toast.makeText(applicationContext,"Profile Picture Uploaded", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(applicationContext,"Error in Uploading", Toast.LENGTH_SHORT).show()
                        }
                        progressBar.dismiss()
                    }
                }else{
                    binding.imageView.setImageResource(R.drawable.profile)
                    Toast.makeText(applicationContext,"Select an Image", Toast.LENGTH_SHORT).show()
                }
            }



        }

        binding.educationAnswer.text = sharedPreferences.getString("userEducation","bacha")
        binding.majorAnswer.text = sharedPreferences.getString("userMajor","nalla hai")
        binding.GenderAnswer.text = sharedPreferences.getString("userGender","ladka hai")
        binding.marriedAnswer.text = sharedPreferences.getString("userMaritial","koi na mil rha ise")
        binding.phoneAnswer.text = sharedPreferences.getString("number","paise nahi")
        binding.emailAnswerAns.text = sharedPreferences.getString("email","not registered")

        binding.emailAnswerAns.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label.toString(), binding.emailAnswerAns.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext,"Copied to ClipBoard", Toast.LENGTH_SHORT).show()
        }

        binding.back.setOnClickListener {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100){
            if (data != null) {
                binding.imageView.setImageURI(data.data)
                isProfilePictureSet = true
                uri = data?.data!!
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}