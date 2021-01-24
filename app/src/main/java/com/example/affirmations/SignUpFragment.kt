package com.example.affirmations

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class SignUpFragment : Fragment() {

    private val TAG = "INSIDE_SIGN_UP_FRAGMENT"
    private var mAuth: FirebaseAuth? = null
    private var xPhone: EditText? = null
    private var xOTP: EditText? = null
    var codeSent: String? = null
    private var pass: EditText? = null
    private lateinit var callbacks: OnVerificationStateChangedCallbacks


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getOTP = view.findViewById<Button>(R.id.getOtp)
        val submitButton = view.findViewById<Button>(R.id.signup)
        xPhone = view.findViewById(R.id.mobileNumber)
        xOTP = view.findViewById(R.id.otp)
        pass=view.findViewById(R.id.password)
        mAuth = FirebaseAuth.getInstance()
        submitButton.visibility=View.GONE
        getOTP.setOnClickListener { sendVerificationCode() }
        submitButton.visibility=View.VISIBLE
        submitButton.setOnClickListener { verifyOTP() }
    }

    private fun verifyOTP() {
        val tOTP = xOTP!!.text.toString().trim { it <= ' ' }
        val passw = pass!!.text.toString().trim { it <= ' ' }
        if (tOTP.isEmpty()) {
            xOTP!!.error = "OTP is required!"
            xOTP!!.requestFocus()
            return
        }
        if (tOTP.length < 6) {
            xOTP!!.error = "Please enter a valid OTP!"
            xOTP!!.requestFocus()
            return
        }
        if (passw.isEmpty()) {
            pass!!.error = "Password is required!"
            pass!!.requestFocus()
            return
        }
        if (TextUtils.isEmpty(codeSent) || codeSent == null) {
            Toast.makeText(activity, "Please wait until code received", Toast.LENGTH_SHORT).show()
            return
        }

        val credential = PhoneAuthProvider.getCredential(codeSent!!, tOTP)
        signInWithPhoneAuthCredential(credential)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                (activity as Executor),
                { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        task.result!!.user
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            xOTP?.error = "Enter Valid OTP!"
                            xOTP?.requestFocus()

                            return@addOnCompleteListener
                        }
                    }
                })
    }

    private fun sendVerificationCode() {
        val xMobileNumber = xPhone!!.text.toString().trim { it <= ' ' }
        if (xMobileNumber.isEmpty()) {
            xPhone!!.error = "Phone number is required!"
            xPhone!!.requestFocus()
            return
        }
        if (xMobileNumber.length < 10) {
            xPhone!!.error = "Please enter a valid phone number!"
            xPhone!!.requestFocus()
            return
        }


            activity?.let {it ->
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        xMobileNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        it,              // Activity (for callback binding) //NOT SURE ABOUT THIS
                        callbacks)
            }        // OnVerificationStateChangedCallbacks



    }
    private var mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
            override fun onVerificationFailed(e: FirebaseException) {}
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                codeSent = s
            }
        }


}




