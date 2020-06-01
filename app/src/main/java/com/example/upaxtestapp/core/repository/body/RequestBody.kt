package com.example.upaxtestapp.core.repository.body

import com.google.gson.annotations.SerializedName

class RequestBody (
    @SerializedName("userId")
    private val userId: Int,
    @SerializedName("env")
    private val env: String,
    @SerializedName("os")
    private val os: String
)