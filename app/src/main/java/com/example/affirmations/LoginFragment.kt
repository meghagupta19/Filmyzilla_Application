package com.example.affirmations


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment



class LoginFragment : Fragment() {

    private var password:EditText?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        password=view.findViewById(R.id.password)

        val pass = password!!.text.toString().trim { it <= ' ' }


            view.findViewById<Button>(R.id.login).setOnClickListener {
                //FirebaseAuth.getInstance().signOut()

                if (pass.length < 8||pass.isEmpty()) {
                    Toast.makeText(activity, "Password must be 8 characters long", Toast.LENGTH_SHORT).show()
                }

                else{
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
               }
            }


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}