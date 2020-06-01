package com.example.upaxtestapp.core.base

import com.example.gonetexam.api.ApiManagerFactory
import com.example.upaxtestapp.App
import com.example.upaxtestapp.BuildConfig
import com.example.upaxtestapp.api.ResponseHandler

open class BaseRepository {
    val application = App.instance
    var service = ApiManagerFactory.makeRetrofitService(BuildConfig.BASE_URL)
    val responseHandler: ResponseHandler = ResponseHandler()

    companion object{

    }
}