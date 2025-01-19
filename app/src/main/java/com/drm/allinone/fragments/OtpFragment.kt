package com.drm.allinone.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.ThirdActivity
import com.drm.allinone.databinding.FragmentOtpBinding
import com.drm.allinone.utils.goToActivity
import com.drm.allinone.utils.showSoftKeyBoard
import com.drm.allinone.utils.showToastLong
import com.drm.allinone.utils.showToastShort
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding
    var otp = ""
    var resendToken:PhoneAuthProvider.ForceResendingToken?=null
    var phoneNumber = ""
    var otpFromUser = ""

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            otp = it.getString("otp")?:""
            resendToken = it.getParcelable("resendToken")
            phoneNumber = it.getString("phoneNumber")?:""
        }


        setListeners()
    }

    private fun setListeners()  = binding.apply{
        psuedoEditText.requestFocus()
        psuedoEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                requireActivity().showSoftKeyBoard(v)
            }
        }
        psuedoEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                otpFromUser = s.toString()

                when(s?.length){
                    0 -> {
                        binding.pin1.text = ""
                        binding.pin2.text = ""
                        binding.pin3.text = ""
                        binding.pin4.text = ""
                        binding.pin5.text = ""
                        binding.pin6.text = ""
                    }
                    1 -> {
                        binding.pin1.text = "${s.toString().toCharArray()[0]}"
                        binding.pin2.text = ""
                        binding.pin3.text = ""
                        binding.pin4.text = ""
                        binding.pin5.text = ""
                        binding.pin6.text = ""
                    }
                    2 -> {
                        binding.pin2.text = "${s.toString().toCharArray()[1]}"
                        binding.pin3.text = ""
                        binding.pin4.text = ""
                        binding.pin5.text = ""
                        binding.pin6.text = ""
                    }
                    3 -> {
                        binding.pin3.text = "${s.toString().toCharArray()[2]}"
                        binding.pin4.text = ""
                        binding.pin5.text = ""
                        binding.pin6.text = ""
                    }
                    4 -> {
                        binding.pin4.text = "${s.toString().toCharArray()[3]}"
                        binding.pin5.text = ""
                        binding.pin6.text = ""
                    }
                    5 -> {
                        binding.pin5.text = "${s.toString().toCharArray()[4]}"
                        binding.pin6.text = ""
                    }
                    6 -> {
                        binding.pin6.text = "${s.toString().toCharArray()[5]}"
                    }
                }

            }

        })


        verifyOtp.setOnClickListener {
            validate()
        }

    }

    private fun validate(){
        if (otpFromUser.isEmpty()){
            requireActivity().showToastShort("Please enter OTP")
        }else if (otpFromUser.length!=6){
            requireActivity().showToastShort("Please enter valid OTP")
        }else{
            val credential = PhoneAuthProvider.getCredential(otp, otpFromUser)
            signInWithPhoneAuthCredential(credential)
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