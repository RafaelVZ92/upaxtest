package com.example.gonetexam.core.base

import androidx.lifecycle.ViewModel
import com.example.upaxtestapp.core.base.BaseRepository

open class BaseViewModel : ViewModel(){
    private val repository by lazy { BaseRepository() }
}