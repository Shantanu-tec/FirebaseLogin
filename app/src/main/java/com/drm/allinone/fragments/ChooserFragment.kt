package com.drm.allinone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.SecondActivity
import com.drm.allinone.databinding.FragmentChooserBinding

class ChooserFragment : Fragment() {

    private lateinit var binding: FragmentChooserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() = binding.apply {
        loginWithEmail.setOnClickListener {
            (activity as SecondActivity).replaceFragment(LoginFragment())
        }
        loginWithPhone.setOnClickListener {
            (activity as SecondActivity).replaceFragment(PhoneLoginFragment())
        }
    }
}