package com.assignment.zooanimalsapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zooanimalsapp.AnimalDataItem
import com.assignment.zooanimalsapp.R
import com.assignment.zooanimalsapp.databinding.AnimalListItemBinding
import com.bumptech.glide.Glide

const val DIET = "Diet: "
const val LIFESPAN = "Lifespan: "
const val YEARS = " yrs"

class AnimalAdapter(
    private var animalList: List<AnimalDataItem>,
    private val context: Context
) : RecyclerView.Adapter<AnimalAdapter.ViewModel>() {


    init {
        animalList = animalList.sortedBy { it.name }
    }

    inner class ViewModel(item: View) : RecyclerView.ViewHolder(item) {
        var binding = AnimalListItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.animal_list_item, parent, false)
        return ViewModel(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        var animal = animalList[position]
        with(holder.binding) {
            animalName.text = animal.name
            animalLatinName.text = animal.latin_name
            animalDiet.text = DIET + animal.diet
            animalLifespan.text = LIFESPAN + animal.lifespan + YEARS + getComment(animal.lifespan)
            Glide.with(context)
                .load(animal.image_link)
                .into(animalImageView);
        }
    }

    private fun getComment(lifespan: String): String {
        return when (lifespan.toInt()) {
            in 0..10 -> " (Not very long!)"
            in 10..20 -> " (Kind of average)"
            else -> " (A long time)"
        }
    }

    override fun getItemCount(): Int {
        return animalList.size
    }
}