package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_gallery, container, false)
        recycler_view.adapter=ItemAdapter(this,Datasource().loadAffirmations())
        return view
    }

}
