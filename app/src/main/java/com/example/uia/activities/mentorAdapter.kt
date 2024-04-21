package com.example.uia.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.view.View.OnClickListener
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uia.R
import com.example.uia.models.Mentor

class mentorAdapter (private var mentorList : ArrayList<Mentor>,
                     private val listener: OnItemClickListener
) : RecyclerView.Adapter<mentorAdapter.myViewHolder>(){

    inner class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        OnClickListener {
            val mentorName : TextView = itemView.findViewById(R.id.mentorName)
            val mentorEmail : TextView = itemView.findViewById(R.id.mentorSocial1)
            val mentorLinkedIn : TextView = itemView.findViewById(R.id.mentorSocial2)
            val workExperience : TextView = itemView.findViewById(R.id.workExperience)
            val mentorWorkAs : TextView = itemView.findViewById(R.id.workAs)
            val viewer : LinearLayout = itemView.findViewById(R.id.layoutMentor)

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(p0: View?) {
                val position : Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mentorconnect_layout,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = mentorList[position]
        holder.mentorName.text = currentItem.name.toString()
        holder.mentorEmail.text = currentItem.email.toString()
        holder.mentorLinkedIn.text = currentItem.linkedIn.toString()
        holder.workExperience.text = currentItem.workExperience.toString()
        holder.mentorWorkAs.text = currentItem.workAs.toString()


    }

    override fun getItemCount(): Int {
        return mentorList.size
    }

}