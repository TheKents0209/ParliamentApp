package com.kml.parliamentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kml.parliamentapp.databinding.FragmentParliamentMemberBinding


class ParliamentMemberFragment : Fragment() {

    private var randomMember = members.randomMember()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentParliamentMemberBinding>(inflater,
            R.layout.fragment_parliament_member,container,false)

        randomMember?.let {
            binding.apply {
                member = randomMember
                invalidateAll()
            }
        }
        binding.memberFirstNameTV.text = randomMember.firstname

        return binding.root
    }

}