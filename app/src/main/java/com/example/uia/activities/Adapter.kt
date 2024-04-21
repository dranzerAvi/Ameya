package com.example.uia.activities

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.uia.R
import com.example.uia.models.Ques

class Adapter(private val context : Activity, private val arrayList : ArrayList<Ques>) : ArrayAdapter<Ques>(context,
            R.layout.tick_mark_check,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate((R.layout.tick_mark_check),null)

        val textView : TextView = view.findViewById(R.id.textBox)
        val value : CheckBox = view.findViewById(R.id.chbox)

        textView.text = arrayList[position].ques
        value.isChecked = arrayList[position].status

        value.setOnClickListener {
            arrayList[position].status = value.isChecked
        }

        return view
    }


}