package com.kml.parliamentapp.ui.main.adapter

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* RecyclerView adapter for List of members
* */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kml.parliamentapp.data.model.ParliamentMember
import androidx.recyclerview.widget.ListAdapter
import com.kml.parliamentapp.databinding.ListItemParliamentMemberBinding

class MemberListAdapter(val clickListener: ParliamentMemberListener) : ListAdapter<ParliamentMember, MemberListAdapter.ViewHolder>(
    ParliamentMemberDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemParliamentMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ParliamentMember, clickListener: ParliamentMemberListener) {
            binding.member = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemParliamentMemberBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }



}

class ParliamentMemberDiffCallback : DiffUtil.ItemCallback<ParliamentMember>() {
    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.hetekaId == newItem.hetekaId
    }

    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem == newItem
    }

}

class ParliamentMemberListener(val clickListener: (hetekaId: Int) -> Unit) {
    fun onClick(member: ParliamentMember) = clickListener(member.hetekaId)
}