package com.kml.parliamentapp.memberlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kml.parliamentapp.database.ParliamentMember
import androidx.recyclerview.widget.ListAdapter
import com.kml.parliamentapp.R
import com.kml.parliamentapp.databinding.ListItemParliamentMemberBinding

class MemberListAdapter(val clickListener: ParliamentMemberListener) :
    ListAdapter<ParliamentMember, MemberListAdapter.ViewHolder>(ParliamentMemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
        holder.itemView.setOnClickListener { view: View ->
            view.findNavController().navigate(
                MemberListFragmentDirections.actionMemberListFragmentToParliamentMemberFragment(item.hetekaId)
            )
        }
    }

    class ViewHolder private constructor(val binding: ListItemParliamentMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ParliamentMember, clickListener: ParliamentMemberListener) {
            binding.member = item
            binding.clickListener = clickListener
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