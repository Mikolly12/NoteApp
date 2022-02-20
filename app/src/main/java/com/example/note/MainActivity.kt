package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IRvAdapter {

    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRvAdapter(this,this)
        recycleView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get((NoteViewModel::class.java))
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateNote(it)
            }

        })
    }

    override fun onItemClicked(note: NoteEntity) {
        viewModel.deleteNote(note)
    }

    fun submitData(view: android.view.View) {

        hideKeybaord(view)
        val noteT= noteText.text.toString()


        if(noteT.isNotEmpty())
        {
            viewModel.addNote(NoteEntity(noteT))

        }
    }

    private fun hideKeybaord(v: View) {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0)
    }
}