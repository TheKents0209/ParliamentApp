package com.kml.parliamentapp.ui.main.view

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Inflates layout, creates viewModel, sets partyList adapter, and observers for viewmodel values
* */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

//TODO: If parties list is empty, throw connection error

class PartyListFragment : Fragment() {

    private lateinit var binding: FragmentPartyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party_list, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = PartyListViewModelFactory(MemberDatabase.getInstance(application).membersDatabaseDao)

        //Creates new viewModel for fragment if one doesn't already exist
        val partyListViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(PartyListViewModel::class.java)

        //Sets the LifecycleOwner that should be used for observing changes of LiveData in this binding
        binding.lifecycleOwner = this

        val adapter = PartyListAdapter(PartyListener { party ->
            partyListViewModel.onPartyClicked(party)
        })

        //Recyclerview custom adapter is set
        binding.partyList.adapter = adapter

        //Observes viewModels parties and submits list if it changes
        partyListViewModel.parties.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        //When party is clicked moves to that partys members
        partyListViewModel.navigateToPartySelected.observe(viewLifecycleOwner, { party ->
            party?.let {
                this.findNavController().navigate(
                    PartyListFragmentDirections
                        .actionPartyListFragmentToMemberListFragment(party))
                partyListViewModel.onNavigateToPartySelected()
            }
        })

        //Layout for Recyclerview
        val manager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL, false)
        binding.partyList.layoutManager = manager

        return binding.root
    }
}