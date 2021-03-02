package com.kml.parliamentapp.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kml.parliamentapp.R
import com.kml.parliamentapp.data.database.MemberDatabase
import com.kml.parliamentapp.databinding.FragmentPartyListBinding
import com.kml.parliamentapp.ui.base.PartyListViewModelFactory
import com.kml.parliamentapp.ui.main.adapter.PartyListAdapter
import com.kml.parliamentapp.ui.main.adapter.PartyListener
import com.kml.parliamentapp.ui.main.viewmodel.PartyListViewModel

class PartyListFragment : Fragment() {

    private lateinit var binding: FragmentPartyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party_list, container, false)


        val application = requireNotNull(this.activity).application

        val viewModelFactory = PartyListViewModelFactory(MemberDatabase.getInstance(application).membersDatabaseDao, application)

        val partyListViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(PartyListViewModel::class.java)

//        binding.partyListViewModel = partyListViewModel
        
        binding.setLifecycleOwner(this)

        val adapter = PartyListAdapter(PartyListener { party ->
            partyListViewModel.onPartyClicked(party)
        })

        binding.partyList.adapter = adapter

        partyListViewModel.parties.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        partyListViewModel.navigateToPartySelected.observe(viewLifecycleOwner, { party ->
            party?.let {

                this.findNavController().navigate(
                    PartyListFragmentDirections
                        .actionPartyListFragmentToMemberListFragment(party))
                partyListViewModel.onNavigateToPartySelected()
            }
        })

        val manager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL, false)
        binding.partyList.layoutManager = manager

        return binding.root
    }
}