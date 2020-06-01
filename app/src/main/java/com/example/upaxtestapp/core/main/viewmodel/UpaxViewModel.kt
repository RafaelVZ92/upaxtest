package com.example.upaxtestapp.core.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.gonetexam.core.base.BaseViewModel
import com.example.upaxtestapp.api.Resource
import com.example.upaxtestapp.core.repository.UpaxRepository
import com.example.upaxtestapp.core.repository.body.RequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpaxViewModel:BaseViewModel(){

    private val mRepository by lazy { UpaxRepository() }

    val mPath = MutableLiveData<Resource<String>>()

    fun getMovie(body: RequestBody) {
        mPath.value = Resource.loading(null)
        GlobalScope.launch {
            val movies = mRepository.upaxData(body)
            withContext(Dispatchers.Main) {
                mPath.value = movies
            }
        }
    }
}