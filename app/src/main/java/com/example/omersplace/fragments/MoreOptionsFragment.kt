package com.example.omersplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.omersplace.R
import com.example.omersplace.databinding.FragmentCartBinding
import com.example.omersplace.databinding.FragmentMoreOptionsBinding
import com.example.omersplace.viewmodels.CartViewModel

class MoreOptionsFragment : Fragment() {
    private lateinit var binding: FragmentMoreOptionsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoreOptionsBinding.inflate(inflater, container, false)


        return binding.root
    }

}