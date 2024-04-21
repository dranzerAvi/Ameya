package com.example.uia

import com.example.uia.constant.Constants
import com.example.uia.constant.Constants.EduStatus

class CommonQuestionnaire {
    companion object{
        val num = arrayOf<String>("What type of area did you live when u were a Child?","What is your Gender?"
        ,"")

        val options = arrayOf(
            arrayOf<String>("Rural", "SubUrban", "Urban"),
            arrayOf<String>("Male", "Female", "Other"),
            EduStatus.values(),
        )
    }
}