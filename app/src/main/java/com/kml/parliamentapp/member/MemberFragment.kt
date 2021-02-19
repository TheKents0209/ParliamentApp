package com.kml.parliamentapp.member

import android.os.Bundle
import android.util.Log
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
import com.kml.parliamentapp.databinding.FragmentMemberBinding

class MemberFragment : Fragment() {

    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates binding layout and returns created binding for layout
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_member,
            container,
            false
        )

        val args = MemberFragmentArgs.fromBundle(requireArguments())

        Log.i("MemberFragment", args.hetekaId.toString())

        fun getClickedHetekaId(): Int {
            return args.hetekaId
        }

        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        val dataSource = MembersDatabase.getInstance(application).membersDatabaseDao
        val viewModelFactory = MemberViewModelFactory(dataSource, application, args.hetekaId)
        // Get a reference to the ViewModel associated with this fragment.
        val memberViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        //memberViewModel.hetekaId.observe(viewLifecycleOwner, Observer { args.hetekaId })

        Log.i("MemberFragment", "Called ViewModelProvider.get")

        //Use ViewModel to manage UI
        binding.memberViewModel = memberViewModel
        //Sets view as LiveData observer
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
