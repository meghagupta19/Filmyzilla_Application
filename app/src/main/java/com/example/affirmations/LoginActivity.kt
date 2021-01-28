package com.example.affirmations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.affirmations.child_fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout



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
        adapter.addFragment(LoginFragment(),"Via Email")
        adapter.addFragment(SignUpFragment(),"Via Phone")

        //tabLayout.getTabAt(0)!!.text = "Login"
        //tabLayout.getTabAt(1)!!.text = "Sign Up"
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.text = "Via Email"
        tabLayout.getTabAt(1)!!.text = "Via Phone"
    }


}