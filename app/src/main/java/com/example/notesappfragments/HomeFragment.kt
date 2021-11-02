package com.example.notesappfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.UI.MyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    lateinit var rv:RecyclerView
    lateinit var etNote:EditText
    lateinit var buAddNote:Button
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("info",Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()

        rv=view.findViewById(R.id.rv)
        etNote=view.findViewById(R.id.etNote)
        buAddNote=view.findViewById(R.id.buAddNote)

        myViewModel.getnotes().observe(viewLifecycleOwner,{
                notes->rv.adapter=myAdapter(notes,this)
        })
        rv.layoutManager=LinearLayoutManager(requireContext())

        buAddNote.setOnClickListener {
            myViewModel.addNote(etNote.text.toString())
            etNote.text.clear()
        }

        return view
    }

}