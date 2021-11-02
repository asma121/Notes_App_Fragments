package com.example.notesappfragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappfragments.UI.MyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpdateFragment : Fragment() {
    lateinit var etEdit:EditText
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)

        etEdit=view.findViewById(R.id.etEdit)
        val sharedPreferences = requireActivity().getSharedPreferences("info", Context.MODE_PRIVATE)

        view.findViewById<Button>(R.id.buUpdate).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_updateFragment_to_homeFragment)
            val id=sharedPreferences.getInt("NoteId",0)
            myViewModel.updateNote(id,etEdit.text.toString())
        }

        return view
    }

}