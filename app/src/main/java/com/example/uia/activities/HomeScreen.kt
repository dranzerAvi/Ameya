package com.example.uia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.uia.databinding.ActivityHomeScreenBinding
import com.example.uia.models.Ques

class HomeScreen : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    lateinit var results : ArrayList<Int>
    var count = 0
    var R = 0
    var I = 0
    var A = 0
    var S = 0
    var E = 0
    var C = 0
    lateinit var listView : ListView

    private lateinit var arrayList : ArrayList<Ques>
    var question = arrayListOf<String>("I like to","I'm Good at","I am","I wouldn’t mind","I Enjoy")

    val options = arrayOf(
        arrayOf<String>("Work on cars", "Work in a team", "Organize things", "Read about art and music", "To Follow Clear instructions", "Influence people", "Do experiment ",
            "Teach or train people ", "Help people to solve their prob ", "Take responsibility ", "Analyze problems ", "Start my own business ",
            "Get into discussions", "Lead"),
        arrayOf<String>("Working independently", "Healing others", "Assembling things", "Working with numbers", "Keeping records",
                "Working outdoors", "Typing", "Cooking", "Solving Puzzles"),
        arrayOf<String>("Ambitious", "A Creative thinker", "A Practical thinker", "A Logical thinker", "A Constructive thinker", "A Musician", "A Helper"),
        arrayOf<String>("Working 8 hours per day", "Helping out people", "Giving a Speech", "Acting", "Caring for an animal", "Selling things"),
        arrayOf<String>("Creative writing", "Learning about Science", "Figuring how things work", "Learning about cultures", "Paying attention to other", "Working in an office",
            "Drawing Figurines", "Giving speeches"),
    )

    val answerSet = arrayOf(
        arrayOf<String>("R", "S", "C","A","C","E","I","S","S","E","I","E","S","E"),
        arrayOf<String>("A", "S", "R","I","C","R","C","R","I"),
        arrayOf<String>("E", "A", "R","I","R","A","S"),
        arrayOf<String>("C", "S", "E","A","R","E"),
        arrayOf<String>("A", "I", "I","S","C","C","A","E"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        arrayList = ArrayList()
        binding.mainQues.text = "Q. " + question[count] + "..."
        for (i in options[count]){
            var ques = Ques(i.toString(),false)
            arrayList.add(ques)
        }
        binding.listView.adapter = Adapter(this,arrayList)

    }

    fun animater(num: Int){
        val intent = Intent(this, shifterClass::class.java)
        intent.putExtra("num",num)
        startActivity(intent)
    }

    fun nextQuestion(view : View){
        animater(count+1)
        if(count == 3)
            binding.btnext.text = "Complete the Test"
        for(i in 0 until arrayList.size){
            if (arrayList[i].status){
                var char = answerSet[count][i]
                when (char) {
                    "R" -> R++
                    "I" -> I++
                    "A" -> A++
                    "S" -> S++
                    "E" -> E++
                    "C" -> C++
                    else -> { // Note the block
                        Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        count++;
        if(count < 5){
            arrayList.clear()
            binding.mainQues.text = "Q. " + question[count] + "..."
            for (i in options[count]){
                var ques = Ques(i.toString(),false)
                arrayList.add(ques)
            }
            binding.listView.adapter = Adapter(this,arrayList)
            binding.count.text = (count+1).toString() + "/5"
        }else{
            results = ArrayList()
            results.add(Integer.valueOf(R.toString()))
            results.add(Integer.valueOf(I.toString()))
            results.add(Integer.valueOf(A.toString()))
            results.add(Integer.valueOf(S.toString()))
            results.add(Integer.valueOf(E.toString()))
            results.add(Integer.valueOf(C.toString()))

            var sum : Double = (R+I+A+S+E+C).toDouble();
            var intent = Intent(this,ResultActivity::class.java)
            intent.putIntegerArrayListExtra("arrayList",results)
            intent.putExtra("sum", sum)
            startActivity(intent)
            /*var intent = Intent(this, ResultActivity::class.java)
            // intent.putExtra("currentUser", currentUser)
            startActivity(intent)*/
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this,ResultScreen::class.java)
        startActivity(intent)
        finish()
    }


}