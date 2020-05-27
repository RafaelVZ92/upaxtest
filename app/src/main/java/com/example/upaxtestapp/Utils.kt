package com.example.upaxtestapp

import kotlin.random.Random


class Utils {
    companion object{
        //This method build random latitude and longitude to print marked in map
        fun getLatLog():Double{
            val min:Double = -100.0
            val max:Double = 100.0
           return Random.nextDouble(min, max)
        }
    }
}