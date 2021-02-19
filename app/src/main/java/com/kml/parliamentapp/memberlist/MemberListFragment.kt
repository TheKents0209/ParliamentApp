package com.kml.parliamentapp.memberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.R
import com.kml.parliamentapp.database.MembersDatabase
import com.kml.parliamentapp.databinding.FragmentMemberListBinding


class MemberListFragment : Fragment() {

    private lateinit var binding: FragmentMemberListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_member_list, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MembersDatabase.getInstance(application).membersDatabaseDao
        val viewModelFactory = MemberListViewModelFactory(dataSource, application)

        val memberListViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MemberListViewModel::class.java)

        binding.memberListViewModel = memberListViewModel

        binding.lifecycleOwner = this

        val adapter = MemberListAdapter(ParliamentMemberListener { hetekaId ->
            Toast.makeText(context, "${hetekaId}", Toast.LENGTH_LONG).show()
        })

        binding.memberList.adapter = adapter

        memberListViewModel.allMembers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        return binding.root
    }
}