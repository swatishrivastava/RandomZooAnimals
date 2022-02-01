package com.assignment.zooanimalsapp.repository

import com.assignment.zooanimalsapp.AnimalDataItem
import com.assignment.zooanimalsapp.network.NetworkResult

interface IRepository {
    suspend fun getAllAnimalList(): NetworkResult<List<AnimalDataItem>>
}