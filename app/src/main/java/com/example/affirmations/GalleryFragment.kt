package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.DataSource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_gallery.view.*


class GalleryFragment : Fragment() {
    private val storage = Firebase.storage
    private var storageRef: StorageReference = storage.getReference()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = ItemAdapter(this, DataSource().loadAffirmations())

        return  view
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        includesForCreateReference()

        download()
    }

    private fun includesForCreateReference() {

        var imagesRef: StorageReference? = storageRef.child("images")

        var spaceRef = storageRef.child("images/space.jpg")

        imagesRef = spaceRef.parent

        val rootRef = spaceRef.root
        // [END navigate_references]

        val earthRef = spaceRef.parent?.child("earth.jpg")

        // nullRef is null, since the parent of root is null
        val nullRef = spaceRef.root.parent

        spaceRef.path

        spaceRef.name

        // Reference's bucket is the name of the storage bucket that the files are stored in
        spaceRef.bucket

        storageRef = storage.reference

        // Points to "images"
        imagesRef = storageRef.child("images")


        val fileName = "space.jpg"
        spaceRef = imagesRef.child(fileName)

        // File path is "images/space.jpg"
        val path = spaceRef.path

        // File name is "space.jpg"
        val name = spaceRef.name

        imagesRef = spaceRef.parent
        // [END reference_full_example]
    }


    private fun download(){

        // Create a reference with an initial file path and name
        var pathReference = storageRef.child("images/stars.jpg")

        // Create a reference to a file from a Google Cloud Storage URI
        var gsReference: StorageReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg")

        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
        var httpsReference: StorageReference =
            storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg")

    }*/

}