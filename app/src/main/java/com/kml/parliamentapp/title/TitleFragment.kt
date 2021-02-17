package com.kml.parliamentapp.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.kml.parliamentapp.R
import com.kml.parliamentapp.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        binding.randomButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_memberListFragment)
        }
        return binding.root
    }
}