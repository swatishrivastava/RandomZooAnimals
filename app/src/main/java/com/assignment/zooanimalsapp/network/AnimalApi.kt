package com.assignment.zooanimalsapp.network

import com.assignment.zooanimalsapp.AnimalDataItem
import retrofit2.http.GET

interface AnimalApi {
    @GET("rand/10/")
    suspend fun getAllAnimals(): List<AnimalDataItem>
}