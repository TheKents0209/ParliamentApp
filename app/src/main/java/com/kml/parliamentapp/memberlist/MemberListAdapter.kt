package com.kml.parliamentapp.memberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kml.parliamentapp.R
import com.kml.parliamentapp.database.ParliamentMember
import androidx.recyclerview.widget.ListAdapter
import com.kml.parliamentapp.databinding.ListItemParliamentMemberBinding

class MemberListAdapter :
    ListAdapter<ParliamentMember, MemberListAdapter.ViewHolder>(ParliamentMemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemParliamentMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val fullname: TextView = binding.memberFullnameTV

        fun bind(item: ParliamentMember) {
            binding.member = item
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