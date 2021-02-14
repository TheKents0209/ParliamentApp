package com.kml.parliamentapp.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.R
import com.kml.parliamentapp.databinding.FragmentMemberBinding

class MemberFragment : Fragment() {

    private lateinit var viewModel: MemberViewModel

    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_member,
            container,
            false
        )

        Log.i("MemberFragment", "Called ViewModelProvider.get")

        //Get the viewModel
        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        binding.memberViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}