package com.example.notesappfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.DB.NoteDetail
import com.example.notesappfragments.UI.MyViewModel
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val notes:List<NoteDetail>, val activity: HomeFragment): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    private val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note=notes[position]
        holder.itemView.apply {
            textView.text=note.note

            imageButtonDelete.setOnClickListener {
                myViewModel.deleteNote(note)
            }

            imageButtonEdit.setOnClickListener {
                activity.editor.putInt("NoteId", note.id)
                activity.editor.commit()
                activity.findNavController().navigate(R.id.action_homeFragment_to_updateFragment)

            }
        }

    }

    override fun getItemCount()=notes.size
}