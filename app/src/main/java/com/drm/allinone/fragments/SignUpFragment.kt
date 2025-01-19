package com.drm.allinone.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.SecondActivity
import com.drm.allinone.ThirdActivity
import com.drm.allinone.databinding.FragmentSignupBinding
import com.drm.allinone.utils.goToActivity
import com.drm.allinone.utils.showToastLong
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }


    private fun setListeners() = binding.apply {
        signUp.setOnClickListener {
            validate()
        }

        loginText.setOnClickListener {
            (requireActivity() as SecondActivity).replaceFragment(LoginFragment(), false)
        }
    }

    private fun validate() = binding.apply {
        if (userName.text.toString().isEmpty()) {
            userName.error = "Email is Empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()) {
            userName.error = "Email is not valid"
        } else if (passWord.text.toString().isEmpty()) {
            passWord.error = "Password is Empty"
        } else if (passWord.text.toString().length != 8) {
            passWord.error = "Password must be of 8 character"
        } else if (confirmPassword.text.toString().isEmpty()) {
            confirmPassword.error = "Password is Empty"
        } else if (confirmPassword.text.toString().length != 8) {
            confirmPassword.error = "Password must be of 8 character"
        } else if (confirmPassword.text.toString() != passWord.text.toString()) {
            confirmPassword.error = "Password do not match"
            passWord.error = "Password do not match"
        } else {
            val username = userName.text.toString()
            val password = passWord.text.toString()

            userName.text.clear()
            passWord.text.clear()
            confirmPassword.text.clear()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        requireActivity().showToastLong("User Registered Successfully")
                        requireActivity().goToActivity(ThirdActivity::class.java)
                    } else {
                        requireActivity().showToastLong("Something Went Wrong")
                    }
                }.addOnFailureListener { exception ->
                    requireActivity().showToastLong(exception.message.toString())
                }


        }
    }

}