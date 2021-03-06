package com.kml.parliamentapp.ui.main.adapter

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* RecyclerView adapter for List of parties
* */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kml.parliamentapp.data.model.Party
import androidx.recyclerview.widget.ListAdapter
import com.kml.parliamentapp.databinding.ListItemPartiesBinding

class PartyListAdapter(private val clickListener: PartyListener) : ListAdapter<Party, PartyListAdapter.ViewHolder>(PartyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //Called by RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
        holder.binding.imageView.bindLogo(getItem(position))
    }

    class ViewHolder private constructor(val binding: ListItemPartiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Binds XML items
        fun bind(item: Party, clickListener: PartyListener) {
            binding.party = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPartiesBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

//ListAdapter diffs the new list against the old one and detects items that were added, removed, moved, or changed.
class PartyDiffCallback : DiffUtil.ItemCallback<Party>() {

    override fun areItemsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem.party == newItem.party
    }

    override fun areContentsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem == newItem
    }
}

class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: Party) = clickListener(party.party)
}