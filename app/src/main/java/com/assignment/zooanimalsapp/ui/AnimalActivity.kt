package com.assignment.zooanimalsapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.zooanimalsapp.AnimalDataItem
import com.assignment.zooanimalsapp.R
import com.assignment.zooanimalsapp.network.AnimalClient
import com.assignment.zooanimalsapp.repository.AnimalRepo
import com.assignment.zooanimalsapp.ui.base.BaseActivity
import com.assignment.zooanimalsapp.utils.getErrorMessageFromCode
import com.hellofresh.task2.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class AnimalActivity : BaseActivity() {

    private lateinit var adapter: AnimalAdapter
    private val animalViewModel: AnimalViewModel by viewModels()

    override fun observeViewModel() {
        observe(animalViewModel.animalLiveData, ::updateListWithAnimalsData)
        observe(animalViewModel.errorLiveData, ::showError)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareList()
    }


    private fun updateListWithAnimalsData(it: List<AnimalDataItem>) {
        progressBar.visibility = View.GONE
        adapter = AnimalAdapter(it, applicationContext)
        animal_list.adapter = adapter
    }

    private fun showError(errorCode: Int) {
        progressBar.visibility = View.GONE
        Toast.makeText(
            this,
            getErrorMessageFromCode(applicationContext, errorCode),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun prepareList() {
        with(animal_list) {
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(divider)
        }
    }
}