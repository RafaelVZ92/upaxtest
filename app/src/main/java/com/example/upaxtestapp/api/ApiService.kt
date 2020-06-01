package com.example.upaxtestapp.api

import com.example.upaxtestapp.core.repository.body.RequestBody
import com.example.upaxtestapp.core.repository.body.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST
    suspend fun upaxData(
        @Body request: RequestBody,
        @Url path: String
    ): Response<ResponseBody>

}