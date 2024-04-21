package com.example.uia.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.*
import android.net.Uri
import android.os.Bundle
import com.example.uia.R
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uia.databinding.ActivityMentorConnectBinding
import com.example.uia.models.Mentor
import com.google.firebase.database.*
import java.util.*

class mentorConnect : AppCompatActivity(),mentorAdapter.OnItemClickListener {

    private lateinit var binding : ActivityMentorConnectBinding
    private lateinit var sharedPreferences : SharedPreferences
    lateinit var mentorArrayList : ArrayList<Mentor>
    lateinit var progressBar : ProgressDialog
    var social = arrayOf("Email", "LinkedIn")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorConnectBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.setHasFixedSize(true)

        mentorArrayList = ArrayList()
        progressBar = ProgressDialog(this)
        progressBar.setTitle("Downloading...")
        progressBar.show()
        getMentorsInTheView()

        binding.registerMentor.setOnClickListener {
            var intent = Intent(this,MentorRegister::class.java)
            startActivity(intent)
        }

    }



    private fun getMentorsInTheView() {
        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Mentor")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var name = ""
                var email = ""
                var linkedIn = ""
                var exp = ""
                var workAs = ""

                for (snapshot_01 : DataSnapshot in snapshot.children){
                    for (snapshot_02 : DataSnapshot in snapshot_01.children){
                        if(Objects.equals(snapshot_02.key,"email"))
                            email = snapshot_02.value.toString()
                        if(Objects.equals(snapshot_02.key,"linkedIn"))
                            linkedIn = snapshot_02.value.toString()
                        if(Objects.equals(snapshot_02.key,"name"))
                            name = snapshot_02.value.toString()
                        if(Objects.equals(snapshot_02.key,"workAs"))
                            workAs = snapshot_02.value.toString()
                        if(Objects.equals(snapshot_02.key,"workExperience"))
                            exp = snapshot_02.value.toString()

                    }
                    val mentorObj = Mentor(name, email, linkedIn, exp, workAs)
                    mentorArrayList.add(mentorObj)
                }
                binding.recView.adapter = mentorAdapter(mentorArrayList,this@mentorConnect)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        progressBar.dismiss()
    }

    override fun onItemClick(position: Int) {
        val currentUser = mentorArrayList[position]
        val email = currentUser.email
        val linkedIn = currentUser.linkedIn
        val addresss = arrayOf(email, linkedIn)

        val builder = AlertDialog.Builder(this@mentorConnect)
        builder.setTitle("Contact Via")
        builder.setIcon(R.drawable.developer_pic)
        builder.setSingleChoiceItems(social, -1,
            DialogInterface.OnClickListener { _, i ->
                if (i == 0){
                    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText(android.R.attr.label.toString(), addresss[i])
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(applicationContext,"Email Copied", Toast.LENGTH_SHORT).show()
                    val email = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, addresss[i])
                        putExtra(Intent.EXTRA_SUBJECT, "Mentoring Session")
                        putExtra(Intent.EXTRA_TEXT, "Respected Mentor,\n")
                    }
                    startActivity(email)
                }
                else if(i == 1) run {
                    var url = "https://www.linkedin.com/in/" + addresss[i] + "/"
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
            })
        builder.setNeutralButton(
            "Cancel"
        ) { _, _ -> }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}