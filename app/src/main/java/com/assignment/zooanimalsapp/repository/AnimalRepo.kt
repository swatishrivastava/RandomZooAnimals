package com.assignment.zooanimalsapp.repository

import com.assignment.zooanimalsapp.network.AnimalApi

class AnimalRepo(private val animalApi: AnimalApi) :IRepository, BaseRepository() {

     override suspend fun getAllAnimalList() = safeApiCall {
        animalApi.getAllAnimals()
    }
}