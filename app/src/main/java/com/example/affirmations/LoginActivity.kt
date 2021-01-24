package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.affirmations.child_fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_Layout)
        viewPager = findViewById(R.id.view_pager)

        setUpTabs()

    }

    private fun setUpTabs() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(),"Login")
        adapter.addFragment(SignUpFragment(),"Sign Up")

        //tabLayout.getTabAt(0)!!.text = "Login"
        //tabLayout.getTabAt(1)!!.text = "Sign Up"
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(view_pager)
        tabLayout.getTabAt(0)!!.text = "Login"
        tabLayout.getTabAt(1)!!.text = "Sign Up"
    }


}