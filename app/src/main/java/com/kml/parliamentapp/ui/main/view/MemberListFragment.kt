package com.kml.parliamentapp.ui.main.view

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Inflates layout, gets arguments, creates viewModel, sets memberList adapter, and observers for viewmodel values
* */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kml.parliamentapp.R
import com.kml.parliamentapp.data.database.MemberDatabase
import com.kml.parliamentapp.databinding.FragmentMemberListBinding
import com.kml.parliamentapp.ui.main.adapter.MemberListAdapter
import com.kml.parliamentapp.ui.base.MemberListViewModelFactory
import com.kml.parliamentapp.ui.main.adapter.ParliamentMemberListener
import com.kml.parliamentapp.ui.main.viewmodel.MemberListViewModel


class MemberListFragment : Fragment() {

    private lateinit var binding: FragmentMemberListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_member_list, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = MemberListFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = MemberListViewModelFactory(MemberDatabase.getInstance(application).membersDatabaseDao, application, arguments.party)

        val memberListViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MemberListViewModel::class.java)

        binding.memberListViewModel = memberListViewModel

        binding.setLifecycleOwner(this)

        val adapter = MemberListAdapter(ParliamentMemberListener { hetekaId ->
            memberListViewModel.onParliamentMemberClicked(hetekaId)
        })

        binding.memberList.adapter = adapter

        memberListViewModel.parliamentMembers.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        memberListViewModel.navigateToParliamentMemberDetails.observe(viewLifecycleOwner, { member ->
            member?.let {

                this.findNavController().navigate(
                    MemberListFragmentDirections
                        .actionMemberListFragmentToParliamentMemberFragment(member))
                memberListViewModel.onParliamentMemberDetailsNavigated()
            }
        })

        return binding.root
    }
}