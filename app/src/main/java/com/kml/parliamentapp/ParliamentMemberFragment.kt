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

    //TODO:add touku's eduskunta API for likes
    var likes = 0

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
        binding.firstNameTV.text = randomMember.firstname
        binding.lastNameTV.text = randomMember.lastname
        binding.likeAmountTV.text = likes.toString()

        binding.likeButton.setOnClickListener {
            likes++
            binding.likeAmountTV.text = likes.toString()
        }
        binding.dislikeButton.setOnClickListener {
            likes--
            binding.likeAmountTV.text = likes.toString()
        }
        binding.randomMemberButton.setOnClickListener {
            randomMember = members.randomMember()
            likes = 0
            randomMember?.let {
                binding.apply {
                    member = randomMember
                    invalidateAll()
                }
            }
            binding.likeAmountTV.text = likes.toString()
        }

        return binding.root
    }

}