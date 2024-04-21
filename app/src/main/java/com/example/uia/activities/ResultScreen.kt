package com.example.uia.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.R
import com.example.uia.databinding.ActivityResultScreenBinding
import com.example.uia.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class ResultScreen : AppCompatActivity() {

    lateinit var binding : ActivityResultScreenBinding
    private lateinit var sharedPreferences : SharedPreferences
    var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users")
    private lateinit var alert : AlertDialog.Builder
    lateinit var editor : SharedPreferences.Editor
    lateinit var popUpWindow : PopupWindow

    private var clicked = false

    private val rotateOpen  : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.open_drawer) }
    private val rotateClose  : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close) }
    private val fromBottom  : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom) }
    private val toBottom  : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.tobottom) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityResultScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        editor  = sharedPreferences.edit()

        alert = AlertDialog.Builder(this)

        binding.userName.text = "Hey, " + sharedPreferences.getString("name","Let's get Started").toString() + "!"

        binding.btn2.isClickable = false
        binding.btn3.isClickable = false
        binding.btn4.isClickable = false
        binding.potential.isClickable = false

        binding.menu.setOnClickListener {
            onAddButtonClicked()
        }

        var flag = sharedPreferences.getString("quiz_status","no")
        if (flag.equals("Done")){
            binding.btn2.isClickable = true
            binding.btn2.alpha = 1F
            binding.career.alpha = 1F
            binding.btn3.isClickable = true
            binding.btn3.alpha = 1F
            binding.mentor.alpha = 1F
            binding.btn4.isClickable = true
            binding.btn4.alpha = 1F
            binding.profile.alpha = 1F
            binding.potential.isClickable = true
            binding.potential.alpha = 1F
        }

        binding.logout.setOnClickListener {
            alert.setTitle("Are you Sure?")
                .setMessage("Are you Sure You want to Logout")
                .setCancelable(true)
                .setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                    editor.clear()
                    editor.apply()
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(applicationContext, LoginScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("No"){ dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.cancel()
                }
                .show()
        }

        binding.feedback.setOnClickListener {
            var url = "https://forms.gle/jwmbjZHdmTjbGu9E8"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        binding.developerProfile.setOnClickListener {
            var intent = Intent(this,Developer_Profile::class.java)
            startActivity(intent)
        }

    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked : Boolean) {
        if (!clicked){
            binding.developerProfile.startAnimation(fromBottom)
            binding.feedback.startAnimation(fromBottom)
            binding.menu.startAnimation(rotateOpen)
        }else{
            binding.developerProfile.startAnimation(toBottom)
            binding.feedback.startAnimation(toBottom)
            binding.menu.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked : Boolean) {
        if (!clicked){
            binding.feedback.visibility = View.VISIBLE
            binding.developerProfile.visibility = View.VISIBLE
        }else{
            binding.feedback.visibility = View.INVISIBLE
            binding.developerProfile.visibility = View.INVISIBLE
        }
    }

//    fun logout(view : View){
//
//    }

    fun reDirect(view : View){
        var char = view.tag
        when (char) {
            "1" -> {
                var intent = Intent(this,HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            "2" -> {
                if (binding.btn2.isClickable){
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/Career-Paths-dff18f3e3af24f7fabdcd8cd2c5d627e"))
                    startActivity(browserIntent)
                }
                else{
                    Toast.makeText(applicationContext,"Complete the Quiz", Toast.LENGTH_SHORT).show()
                }
            }
            "3" -> {
                if (binding.btn3.isClickable){
                    val intent = Intent(this,mentorConnect::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Complete the Quiz", Toast.LENGTH_SHORT).show()
                }

            }
            "4" -> {
                if (binding.btn4.isClickable){
                    var intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Complete the Quiz", Toast.LENGTH_SHORT).show()
                }
            }
            "5" -> {
                if (binding.potential.isClickable){
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://erratic-touch-34b.notion.site/Unconventional-Career-Stories-5cf355a518784b36b8cb26e904419a4c"))
                    startActivity(browserIntent)
                }else{
                    Toast.makeText(applicationContext,"Complete the Quiz", Toast.LENGTH_SHORT).show()
                }
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}