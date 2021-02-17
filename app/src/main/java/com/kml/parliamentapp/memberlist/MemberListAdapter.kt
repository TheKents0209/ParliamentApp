package com.kml.parliamentapp.memberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kml.parliamentapp.R
import com.kml.parliamentapp.TextItemViewHolder
import com.kml.parliamentapp.database.ParliamentMember

class MemberListAdapter : RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {

    var data = listOf<ParliamentMember>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullname: TextView = itemView.findViewById(R.id.member_fullnameTV)

        fun bind(item: ParliamentMember) {
            val res = itemView.context.resources
            fullname.text = item.fullname
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_parliament_member, parent, false)
                return ViewHolder(view)
            }
        }
    }

}