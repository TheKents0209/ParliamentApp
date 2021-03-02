package com.kml.parliamentapp.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.R
import com.kml.parliamentapp.data.database.LikesDatabase
import com.kml.parliamentapp.data.database.MemberDatabase
import com.kml.parliamentapp.databinding.FragmentMemberBinding
import com.kml.parliamentapp.ui.main.view.MemberFragmentArgs
import com.kml.parliamentapp.ui.base.MemberViewModelFactory
import com.kml.parliamentapp.ui.main.viewmodel.MemberViewModel

class MemberFragment : Fragment() {

    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Inflates binding layout and returns created binding for layout
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_member,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val arguments = MemberFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = MemberDatabase.getInstance(application).membersDatabaseDao
        val likesDataSource = LikesDatabase.getInstance(application).likesDatabaseDao
        val viewModelFactory = MemberViewModelFactory(dataSource,likesDataSource,application, arguments.hetekaId)
        // Get a reference to the ViewModel associated with this fragment.
        val memberViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        //Use ViewModel to manage UI
        binding.memberViewModel = memberViewModel
        //Sets view as LiveData observer
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
