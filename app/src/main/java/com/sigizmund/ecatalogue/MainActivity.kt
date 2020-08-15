package com.sigizmund.ecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.spinner_view.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: LinearLayoutManager

    private val myDataset = arrayOf("Book 1", "Book 2", "Book 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)
        list_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }

        ArrayAdapter.createFromResource(
            this,
            R.array.BreakdownTypes,
            R.layout.spinner_view
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_view)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }
}
