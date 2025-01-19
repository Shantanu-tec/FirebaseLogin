package com.drm.allinone.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.SecondActivity
import com.drm.allinone.ThirdActivity
import com.drm.allinone.databinding.FragmentLoginBinding
import com.drm.allinone.utils.goToActivityWithFinish
import com.drm.allinone.utils.showToastLong
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    //Pair
    //Triple

    private val loginList = arrayListOf(Pair("Karan","12345678"),Pair("Tarun","12345678"),Pair("Rahul","12345678"))
    private val loginMap = mapOf("Karan" to "12345678","Tarun" to "12345678","Rahul" to "12345678")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }


    private fun setListeners() = binding.apply {
        login.setOnClickListener {
            validate()
        }

        signUpText.setOnClickListener {
            (requireActivity() as SecondActivity).replaceFragment(SignUpFragment())
        }
    }

    private fun validate() = binding.apply{       
        if (userName.text.toString().isEmpty()){
            userName.error = "Email is Empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()) {
            userName.error = "Email is not valid"
        }else if (passWord.text.toString().isEmpty()){
            passWord.error = "Password is Empty"
        } else if (passWord.text.toString().length != 8) {
            passWord.error = "Password must be of 8 character"
        } else{
            val username = userName.text.toString()
            val password = passWord.text.toString()


            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username,password)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        userName.text.clear()
                        passWord.text.clear()
                        requireActivity().showToastLong("Login Successfully")
                        requireActivity().goToActivityWithFinish(ThirdActivity::class.java)
                    } else {
                        requireActivity().showToastLong("Invalid Email Password")
                    }
                }
                .addOnFailureListener { exception->
                    requireActivity().showToastLong(exception.message.toString())
                }
        }
    }

}