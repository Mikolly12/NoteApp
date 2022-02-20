package com.example.note

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRvAdapter(private val context : Context,private val listener: IRvAdapter): RecyclerView.Adapter<NoteRvAdapter.noteViewHolder>() {

    val allNotes = ArrayList<NoteEntity>()

    inner class noteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textview = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        val viewHolder = noteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
                listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val current = allNotes[position]
        holder.textview.text = current.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNote(newList: List<NoteEntity>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface IRvAdapter
{
    fun onItemClicked(note: NoteEntity)
}