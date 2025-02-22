package com.drm.allinone.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.drm.allinone.SecondActivity
import com.drm.allinone.ThirdActivity
import com.drm.allinone.databinding.FragmentLoginBinding
import com.drm.allinone.factory.MyViewModelFactory
import com.drm.allinone.repository.AuthRepositoryImpl
import com.drm.allinone.utils.Resource
import com.drm.allinone.utils.goToActivityWithFinish
import com.drm.allinone.utils.showToastLong
import com.drm.allinone.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val loginList =
        arrayListOf(Pair("Karan", "12345678"), Pair("Tarun", "12345678"), Pair("Rahul", "12345678"))
    private val loginMap =
        mapOf("Karan" to "12345678", "Tarun" to "12345678", "Rahul" to "12345678")

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
        val authRepository = AuthRepositoryImpl(firebaseAuth)
        val viewModelFactory = MyViewModelFactory(authRepository)
        loginViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
        setListeners()
        setObservers()
    }

    private fun setObservers() = binding.apply {
        CoroutineScope(Dispatchers.Main).launch {
            loginViewModel.loginLiveData.collect { result ->
                when (result) {
                    is Resource.Loading -> binding.progressBar.isVisible = true
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        userName.text.clear()
                        passWord.text.clear()
                        requireActivity().showToastLong("Login Successfully")
                        requireActivity().goToActivityWithFinish(ThirdActivity::class.java)
                    }

                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        requireActivity().showToastLong(result.message.toString())
                    }
                }
            }
        }
    }


    private fun setListeners() = binding.apply {
        login.setOnClickListener {
            validate()
        }

        signUpText.setOnClickListener {
            (requireActivity() as SecondActivity).replaceFragment(SignUpFragment())
        }
    }

    //MVC

    private fun validate() = binding.apply {
        if (userName.text.toString().isEmpty()) {
            userName.error = "Email is Empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()) {
            userName.error = "Email is not valid"
        } else if (passWord.text.toString().isEmpty()) {
            passWord.error = "Password is Empty"
        } else if (passWord.text.toString().length != 8) {
            passWord.error = "Password must be of 8 character"
        } else {
            val username = userName.text.toString()
            val password = passWord.text.toString()
            loginViewModel.loginUser(username, password)
        }
    }


//            CoroutineScope(Dispatchers.Main).launch {
//                val result = FirebaseAuth.getInstance()
//                    .signInWithEmailAndPassword(username, password)
//
//                binding.progressBar.isVisible = true
//                result.await()
//
//                if (result.isSuccessful) {
//                    binding.progressBar.isVisible = false
//                    userName.text.clear()
//                    passWord.text.clear()
//                    requireActivity().showToastLong("Login Successfully")
//                    requireActivity().goToActivityWithFinish(ThirdActivity::class.java)
//                } else {
//                    binding.progressBar.isVisible = false
//                    requireActivity().showToastLong("Invalid Email Password")
//                }
//            }

//            FirebaseAuth.getInstance()
//                .signInWithEmailAndPassword(username, password).addOnCompleteListener { result ->
//                    if (result.isSuccessful) {
//                        userName.text.clear()
//                        passWord.text.clear()
//                        requireActivity().showToastLong("Login Successfully")
//                        requireActivity().goToActivityWithFinish(ThirdActivity::class.java)
//                    } else {
//                        requireActivity().showToastLong("Invalid Email Password")
//                    }
//                }.addOnFailureListener { exception ->
//                    requireActivity().showToastLong(exception.message.toString())
//                }


    //Retrofit

    //MVVM : Model View ViewModel
    //ViewModel : Act as a bridge between Model ( Api Repository ya local repository ) and View(XML + Kotlin)
    //Repository : We need Data from repository, so The viewmodel can give it to View Class.
    //Data : Jetpack Components : Live Data, Flow.
    //Repository, ViewModel (LiveData or Flow), ViewModelFactory.
    //
}