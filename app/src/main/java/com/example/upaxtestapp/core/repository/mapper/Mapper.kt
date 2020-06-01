package com.example.upaxtestapp.core.repository.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
