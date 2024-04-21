package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.databinding.ActivityResultBinding
import ir.mahozad.android.PieChart
import ir.mahozad.android.component.Alignment
import ir.mahozad.android.unit.Dimension

class ResultActivity : AppCompatActivity() {

    lateinit var binding : ActivityResultBinding
    lateinit var results : ArrayList<Int>
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityResultBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

//        val pieChart = findViewById<PieChart>(R.id.pieChart)
        results = intent.getIntegerArrayListExtra("arrayList") as ArrayList<Int>
        var sum : Double = intent.getDoubleExtra("sum", 1.0)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.pieChart.apply {
            slices = listOf(
                PieChart.Slice((results.get(0)/sum).toFloat(), Color.rgb(141, 161, 185),label = "R",legend = "Realistic"),
                PieChart.Slice((results.get(1)/sum).toFloat(), Color.rgb(149, 173, 182),label = "I",legend = "Investigative"),
                PieChart.Slice((results.get(2)/sum).toFloat(), Color.rgb(203, 179, 191),label = "A",legend = "Artistic"),
                PieChart.Slice((results.get(3)/sum).toFloat(), Color.rgb(219, 199, 190),label = "S",legend = "Social"),
                PieChart.Slice((results.get(4)/sum).toFloat(), Color.rgb(229, 174, 173),label = "E",legend = "Enterprising"),
                PieChart.Slice((results.get(5)/sum).toFloat(), Color.rgb(239, 149, 156),label = "C",legend = "Conventional"),
            )
            centerLabelIconTint = Color.rgb(255, 0, 0)
        }
        binding.pieChart.apply {
            isLegendEnabled = true
            legendBoxAlignment = Alignment.CENTER
            isLegendBoxBorderEnabled = true
            legendsIcon = PieChart.DefaultIcons.SLICE2
        }

        sharedPreferences.edit().putString("quiz_status","Done").apply()

        var max = 0
        var index = -1
        for(i in 0..results.size-1){
            if(max <= results.get(i)) {
                max = results.get(i)
                index = i
            }
        }
        var word = ""
        when (index) {
            0 -> word = "R"
            1 -> word = "I"
            2 -> word = "A"
            3 -> word = "S"
            4 -> word = "E"
            5 -> word = "C"
            else -> { // Note the block

            }
        }

        when (word) {
            "R" -> binding.approach.text = "Your mind have a Realistic Approach!"
            "I" -> binding.approach.text = "Your mind have an Investigative Approach!"
            "A" -> binding.approach.text = "Your mind have an Artistic Approach!"
            "S" ->  binding.approach.text = "Your mind have a Social Approach!"
            "E" -> binding.approach.text = "Your mind have an Enterprising Approach!"
            "C" -> binding.approach.text = "Your mind have a Conventional Approach!"
            else -> { // Note the block

            }
        }

        var secondMax = 0
        var twoIndex = -1
        for(i in 0..results.size-1){
            if(secondMax < max && results.get(i) > secondMax && results.get(i) != max){
                secondMax = results.get(i)
                twoIndex = i
            }
        }
        var dusraword = ""
        when (twoIndex) {
            0 -> dusraword = "R"
            1 -> dusraword = "I"
            2 -> dusraword = "A"
            3 -> dusraword = "S"
            4 -> dusraword = "E"
            5 -> dusraword = "C"
            else -> { // Note the block

            }
        }

        binding.summaryBt.setOnClickListener {
            var intent = Intent(this,summary_quiz::class.java)
            intent.putExtra("pahilaword",word)
            intent.putExtra("dusraword",dusraword)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this,ResultScreen::class.java)
        startActivity(intent)
        finish()
    }

}