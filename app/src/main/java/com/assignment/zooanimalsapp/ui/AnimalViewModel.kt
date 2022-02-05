package com.assignment.zooanimalsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.zooanimalsapp.AnimalDataItem
import com.assignment.zooanimalsapp.network.NetworkResult
import com.assignment.zooanimalsapp.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject
constructor(private val repo: IRepository) : ViewModel() {

    private var mutableLiveData = MutableLiveData<List<AnimalDataItem>>()
    val animalLiveData: LiveData<List<AnimalDataItem>>
        get() = mutableLiveData

    private var mutableErrorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int>
        get() = mutableErrorLiveData


    init {
        getAllAnimals()
    }

     fun getAllAnimals() {
        viewModelScope.launch {
            var result = repo.getAllAnimalList()
            handleResponse(result)
        }
    }

    private fun handleResponse(result: NetworkResult<List<AnimalDataItem>>) {
        when (result) {
            is NetworkResult.Success -> {
                result.value?.also { mutableLiveData.value = it }
            }
            is NetworkResult.Failure -> {
                result.errorCode?.also { mutableErrorLiveData.value = it }
            }

        }
    }


}