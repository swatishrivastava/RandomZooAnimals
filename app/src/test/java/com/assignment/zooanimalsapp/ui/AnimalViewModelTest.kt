package com.assignment.zooanimalsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.assignment.zooanimalsapp.AnimalDataItem
import com.assignment.zooanimalsapp.network.NetworkResult
import com.assignment.zooanimalsapp.repository.IRepository
import com.assignment.zooanimalsapp.utils.InstantExecutorExtension
import com.assignment.zooanimalsapp.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class AnimalViewModelTest {

    private lateinit var animalViewModel: AnimalViewModel

    @MockK
    private lateinit var dataRepo: IRepository

    @MockK
    private lateinit var result: NetworkResult<List<AnimalDataItem>>

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        animalViewModel = AnimalViewModel(dataRepo)
    }


    fun getAnimalList(): List<AnimalDataItem> {
        var arrayList = mutableListOf<AnimalDataItem>()
        var animalDataItem = AnimalDataItem(
            "Diurnal",
            "Reptile", "Leaves and fruit",
            "United States and northwestern Mexico",
            "Rocky desert and thorn forest",
            54, "https://upload.wikimedia.org/wikipedia/commons/1/15/Sauromalus_ater_%283%29.jpg",
            "Sauromalus ater", "1.3", "1", "15",
            "Chuckwalla", "2", "1.6"

        )
        arrayList.add(animalDataItem)
        return arrayList
    }

    @Test
    fun `test when received empty animal list`() {
        coEvery { dataRepo.getAllAnimalList() } returns NetworkResult.Success(emptyList())
        animalViewModel.getAllAnimals()
        animalViewModel.animalLiveData.observeForever {}
        Assert.assertEquals(true, animalViewModel.animalLiveData.value?.isEmpty())
    }


    @Test
    fun `test to get animal list`() {
        coEvery { dataRepo.getAllAnimalList() } returns NetworkResult.Success(getAnimalList())
        animalViewModel.getAllAnimals()
        animalViewModel.animalLiveData.observeForever {}
        Assert.assertEquals(getAnimalList().get(0), animalViewModel.animalLiveData.value?.get(0))
        Assert.assertEquals(false, animalViewModel.animalLiveData.value?.isEmpty())
    }

    @Test
    fun `test when server unreachable and received error`() {
        coEvery { dataRepo.getAllAnimalList() } returns NetworkResult.Failure(
            true, 500,
            null
        )
        animalViewModel.getAllAnimals()
        animalViewModel.errorLiveData.observeForever {}
        Assert.assertEquals(500, animalViewModel.errorLiveData.value)
    }

}