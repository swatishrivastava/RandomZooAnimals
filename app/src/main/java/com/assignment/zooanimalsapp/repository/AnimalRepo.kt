package com.assignment.zooanimalsapp.repository

import com.assignment.zooanimalsapp.network.AnimalApi
import javax.inject.Inject


class AnimalRepo @Inject
    constructor(private val animalApi: AnimalApi) :IRepository, BaseRepository() {

     override suspend fun getAllAnimalList() = safeApiCall {
        animalApi.getAllAnimals()
    }
}