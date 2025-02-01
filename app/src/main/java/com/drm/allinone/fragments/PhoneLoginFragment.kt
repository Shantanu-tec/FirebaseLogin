package com.drm.allinone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.SecondActivity
import com.drm.allinone.ThirdActivity
import com.drm.allinone.databinding.FragmentPhoneLoginBinding
import com.drm.allinone.utils.goToActivity
import com.drm.allinone.utils.isValidIndianPhoneNumber
import com.drm.allinone.utils.showToastLong
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneLoginFragment : Fragment() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentPhoneLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneLoginBinding.inflate(inflater,container,false)
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
    }


    private fun validate() = binding.apply {
        if (phoneNumber.text.isNullOrEmpty()){
            phoneNumber.error = "Enter Phone Number"
        }else if (phoneNumber.text.length != 10){
            phoneNumber.error = "Enter Phone Number of 10 digit"
        }else if (!isValidIndianPhoneNumber(phoneNumber.text.toString())){
            phoneNumber.error = "Enter Valid Phone Number"
        } else {
            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+91${phoneNumber.text}") // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(requireActivity()) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }



    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            requireActivity().showToastLong(e.message.toString())

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {

            val bundle = Bundle()
            bundle.putString("otp", verificationId)
            bundle.putParcelable("resendToken", token)
            bundle.putString("phoneNumber", binding.phoneNumber.text.toString())

            val otpFragment = OtpFragment()
            otpFragment.arguments = bundle

//            requireActivity().showToastLong("Otp is : $verificationId")


            (requireActivity() as SecondActivity).replaceFragment(otpFragment)


        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user

                    requireActivity().goToActivity(ThirdActivity::class.java)
                } else {
                    requireActivity().showToastLong("Something Went Wrong")
                }
            }
    }

}