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
    private var mobileNumber:EditText?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        password=view.findViewById(R.id.password)
        mobileNumber=view.findViewById(R.id.mobileNumber)

        val pass = password!!.text.toString().trim { it <= ' ' }
        val mob = mobileNumber!!.text.toString().trim { it <= ' ' }


            view.findViewById<Button>(R.id.login).setOnClickListener {
                //FirebaseAuth.getInstance().signOut()

                if (pass.isEmpty()||mob.isEmpty()) {
                    Toast.makeText(activity, "Fields are required", Toast.LENGTH_SHORT).show()
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