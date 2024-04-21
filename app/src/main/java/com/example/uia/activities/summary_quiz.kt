package com.example.uia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.R
import com.example.uia.databinding.ActivityResultScreenBinding
import com.example.uia.databinding.ActivitySummaryQuizBinding

class summary_quiz : AppCompatActivity() {

    lateinit var binding : ActivitySummaryQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySummaryQuizBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var word = intent.getStringExtra("pahilaword")
        var link = ""

        when (word) {
            "R" -> {
                binding.tv1.text = "Engineering"
                binding.tv2.text = "Computers"
                binding.tv3.text = "Mechanic/Machinist"
                binding.tv4.text = "Health Assistant"
                binding.tv5.text = "Construction"
                binding.tv6.text = "Agriculture"
                binding.tv7.text = "Food and Hospitality"
                link = "https://murindanyi.medium.com/realistic-1c660d90a0f2"
            }
            "I" -> {
                binding.tv1.text = "Engineering"
                binding.tv2.text = "Medicine/Surgery"
                binding.tv3.text = "Psychology"
                binding.tv4.text = "Chemistry"
                binding.tv5.text = "Marine Biology"
                binding.tv6.text = "Consumer Economics"
                binding.tv7.text = "Zoology"
                link = "https://murindanyi.medium.com/investigative-1d41a25c4d27"
            }
            "A" -> {
                binding.tv1.text = "Architecture"
                binding.tv2.text = "Communications"
                binding.tv3.text = "Fine and Performing Arts"
                binding.tv4.text = "Photography"
                binding.tv5.text = "Radio and TV"
                binding.tv6.text = "Interior Design"
                binding.tv7.text = "Cosmetology"
                link = "https://medium.com/@devcollins.test/artistic-c2f7b8954ca6"
            }
            "S" -> {
                binding.tv1.text = "Public Relations"
                binding.tv2.text = "Physical Therapy"
                binding.tv3.text = "Travel"
                binding.tv4.text = "Education"
                binding.tv5.text = "Counseling"
                binding.tv6.text = "Nursing"
                binding.tv7.text = "Advertising"
                link = "https://medium.com/@devcollins.test/social-59cad4c14d51"
            }
            "E" -> {
                binding.tv1.text = "Banking/Finance"
                binding.tv2.text = "Marketing/Sales"
                binding.tv3.text = "International Trade"
                binding.tv4.text = "Real Estate"
                binding.tv5.text = "Law"
                binding.tv6.text = "Political Science"
                binding.tv7.text = "Fashion Merchandising"
                link = "https://murindanyi.medium.com/enterprising-74dc2ae0c344"
            }
            "C" -> {
                binding.tv1.text = "Data Processing"
                binding.tv2.text = "Banking"
                binding.tv3.text = "Administration"
                binding.tv4.text = "Accounting"
                binding.tv5.text = "Medical Records"
                binding.tv6.text = "Insurance"
                binding.tv7.text = "Court Reporting"
                link = "https://medium.com/@devcollins.test/conventional-45779d81c9ca"
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }

        var word2 = intent.getStringExtra("dusraword")
        var link2 = ""
        when (word2) {
            "R" -> {
                binding.sidetv1.text = "Engineering"
                binding.sidetv2.text = "Computers"
                binding.sidetv3.text = "Mechanic/Machinist"
                binding.sidetv4.text = "Health Assistant"
                binding.sidetv5.text = "Construction"
                binding.sidetv6.text = "Agriculture"
                binding.sidetv7.text = "Food and Hospitality"
                link2 = "https://murindanyi.medium.com/realistic-1c660d90a0f2"
            }
            "I" -> {
                binding.sidetv1.text = "Medicine/Surgery"
                binding.sidetv2.text = "Engineering"
                binding.sidetv3.text = "Psychology"
                binding.sidetv4.text = "Chemistry"
                binding.sidetv5.text = "Marine Biology"
                binding.sidetv6.text = "Consumer Economics"
                binding.sidetv7.text = "Zoology"
                link2 = "https://murindanyi.medium.com/investigative-1d41a25c4d27"
            }
            "A" -> {
                binding.sidetv1.text = "Architecture"
                binding.sidetv2.text = "Communications"
                binding.sidetv3.text = "Fine and Performing Arts"
                binding.sidetv4.text = "Photography"
                binding.sidetv5.text = "Radio and TV"
                binding.sidetv6.text = "Interior Design"
                binding.sidetv7.text = "Cosmetology"
                link2 = "https://medium.com/@devcollins.test/artistic-c2f7b8954ca6"
            }
            "S" -> {
                binding.sidetv1.text = "Public Relations"
                binding.sidetv2.text = "Physical Therapy"
                binding.sidetv3.text = "Travel"
                binding.sidetv4.text = "Education"
                binding.sidetv5.text = "Counseling"
                binding.sidetv6.text = "Nursing"
                binding.sidetv7.text = "Advertising"
                link2 = "https://medium.com/@devcollins.test/social-59cad4c14d51"
            }
            "E" -> {
                binding.sidetv1.text = "Banking/Finance"
                binding.sidetv2.text = "Marketing/Sales"
                binding.sidetv3.text = "International Trade"
                binding.sidetv4.text = "Real Estate"
                binding.sidetv5.text = "Law"
                binding.sidetv6.text = "Political Science"
                binding.sidetv7.text = "Fashion Merchandising"
                link2 = "https://murindanyi.medium.com/enterprising-74dc2ae0c344"
            }
            "C" -> {
                binding.sidetv1.text = "Data Processing"
                binding.sidetv2.text = "Accounting"
                binding.sidetv3.text = "Administration"
                binding.sidetv4.text = "Banking"
                binding.sidetv5.text = "Medical Records"
                binding.sidetv6.text = "Insurance"
                binding.sidetv7.text = "Court Reporting"
                link2 = "https://medium.com/@devcollins.test/conventional-45779d81c9ca"
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.explore.setOnClickListener {
            var intent = Intent(this,webview::class.java)
            intent.putExtra("url",link)
            startActivity(intent)
        }

        binding.explore2.setOnClickListener {
            var intent = Intent(this,webview::class.java)
            intent.putExtra("url",link2)
            startActivity(intent)
        }

        binding.backtoHome.setOnClickListener {
            var intent = Intent(this,ResultScreen::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}