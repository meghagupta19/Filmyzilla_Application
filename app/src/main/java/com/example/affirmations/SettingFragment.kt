package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_setting2, container, false)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setUpTabs()
    }

    private fun setUpTabs(){
        val adapter=ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PolicyFragment(),"Info")
        viewPager.adapter=adapter
        toolbar2.setupWithViewPager(viewPager)
        toolbar2.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_info_24)

    }*/

}