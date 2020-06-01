package com.example.upaxtestapp.core.repository

import com.example.upaxtestapp.R
import com.example.upaxtestapp.api.HeaderFactory
import com.example.upaxtestapp.api.Resource
import com.example.upaxtestapp.core.base.BaseRepository
import com.example.upaxtestapp.core.repository.body.RequestBody

class UpaxRepository : BaseRepository(){

    suspend fun upaxData(request: RequestBody): Resource<String> {
        return try {
            val upaxData = service.upaxData(
                request,
                application.resources.getString(R.string.upax_path)
            )
            if (!upaxData.isSuccessful) {
                return responseHandler.handleException(upaxData.headers())
            }
            return responseHandler.handleSuccess(
                upaxData.message()
            )
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}