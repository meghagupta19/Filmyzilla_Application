package com.example.affirmations


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var auth: FirebaseAuth = Firebase.auth
    private var password: EditText? =null
    private var email:EditText?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        password=view.findViewById(R.id.password)

            view.findViewById<Button>(R.id.login).setOnClickListener {
                //FirebaseAuth.getInstance().signOut()

                val pass = password!!.text.toString().trim { it <= ' ' }



                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)

            }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    /*public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }
    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                              Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // [START_EXCLUDE]

                    // [END_EXCLUDE]
                }
        // [END create_user_with_email]
    }
    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        // [START_EXCLUDE]
                        checkForMultiFactorFailure(task.exception!!)
                        // [END_EXCLUDE]
                    }

                    // [START_EXCLUDE]
                    if (!task.isSuccessful) {
                        binding.status.setText(R.string.auth_failed)
                    }
                    hideProgressBar()
                    // [END_EXCLUDE]
                }
        // [END sign_in_with_email]
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        // Disable button
        binding.verifyEmailButton.isEnabled = false

        // Send verification email
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
                .addOnCompleteListener(this) { task ->
                    // [START_EXCLUDE]
                    // Re-enable button
                    binding.verifyEmailButton.isEnabled = true

                    if (task.isSuccessful) {
                        Toast.makeText(baseContext,
                                "Verification email sent to ${user.email} ",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.exception)
                        Toast.makeText(baseContext,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                    // [END_EXCLUDE]
                }
        // [END send_email_verification]
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                Toast.makeText(this@EmailPasswordActivity,
                        "Reload successful!",
                        Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(this@EmailPasswordActivity,
                        "Failed to reload user.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = email?.text.toString()
        if (TextUtils.isEmpty(email)) {
            email.setError = "Required."
            valid = false
        } else {
            email.error = null
        }

        val password = password?.text.toString()
        if (TextUtils.isEmpty(password)) {
            password.error = "Required."
            valid = false
        } else {
            password.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {
            binding.status.text = getString(R.string.emailpassword_status_fmt,
                    user.email, user.isEmailVerified)
            binding.detail.text = getString(R.string.firebase_status_fmt, user.uid)

            binding.emailPasswordButtons.visibility = View.GONE
            binding.emailPasswordFields.visibility = View.GONE
            binding.signedInButtons.visibility = View.VISIBLE

            if (user.isEmailVerified) {
                binding.verifyEmailButton.visibility = View.GONE
            } else {
                binding.verifyEmailButton.visibility = View.VISIBLE
            }
        } else {
            binding.status.setText(R.string.signed_out)
            binding.detail.text = null

            binding.emailPasswordButtons.visibility = View.VISIBLE
            binding.emailPasswordFields.visibility = View.VISIBLE
            binding.signedInButtons.visibility = View.GONE
        }
    }

    private fun checkForMultiFactorFailure(e: Exception) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        if (e is FirebaseAuthMultiFactorException) {
            Log.w(TAG, "multiFactorFailure", e)
            val intent = Intent()
            val resolver = e.resolver
            intent.putExtra("EXTRA_MFA_RESOLVER", resolver)
            setResult(MultiFactorActivity.RESULT_NEEDS_MFA_SIGN_IN, intent)
            finish()
        }
    }

     fun onClick(v: View) {
        when (v.id) {
            R.id.emailCreateAccountButton -> {
                createAccount(email?.text.toString(), password?.text.toString())
            }
            R.id.login -> signIn(email?.text.toString(), password?.text.toString())
            //R.id.signOutButton -> signOut()
            //R.id.verifyEmailButton -> sendEmailVerification()
            //R.id.reloadButton -> reload()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
        private const val RC_MULTI_FACTOR = 9005
    }*/

}