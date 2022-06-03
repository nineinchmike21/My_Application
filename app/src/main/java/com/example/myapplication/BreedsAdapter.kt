package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Breed

class BreedsAdapter : ListAdapter<Breed, BreedsAdapter.BreedsViewHolder>(DiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BreedsViewHolder(inflater.inflate(R.layout.item_breed, parent,false))
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        val breed = getItem(position)
        holder.breed.text = breed.name
    }

    private class DiffCallback : DiffUtil.ItemCallback<Breed>(){
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }

    }

    inner class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breed = itemView.findViewById<TextView>(R.id.tv_breed_name) as TextView
    }
}