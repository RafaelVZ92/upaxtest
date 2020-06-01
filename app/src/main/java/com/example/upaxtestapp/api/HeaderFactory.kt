package com.example.upaxtestapp.api

import java.util.HashMap

class HeaderFactory {
    companion object{
        private var emptyHeaders: Pair<String, String> = Pair("", "")
        fun getGenericHeader() : HashMap<String, String> {
            return hashMapOf(emptyHeaders)
        }
    }
}