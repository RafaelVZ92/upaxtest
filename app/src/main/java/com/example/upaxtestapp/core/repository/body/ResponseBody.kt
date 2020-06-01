package com.example.upaxtestapp.core.repository.body

import com.google.gson.annotations.SerializedName

class ResponseBody (
    @SerializedName("code")
    private val code: String,
    @SerializedName("message")
    private val message: String,
    @SerializedName("response")
    private val response: Any? = null,
    @SerializedName("success")
    private val success: Boolean
)
