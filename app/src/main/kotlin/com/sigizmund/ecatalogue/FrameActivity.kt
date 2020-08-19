package com.sigizmund.ecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_frame.*

class FrameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        ArrayAdapter.createFromResource(
            this,
            R.array.BreakdownTypes,
            R.layout.spinner_view
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item_view)
            // Apply the adapter to the spinner
            category_spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.SortType,
            R.layout.spinner_view
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item_view)
            sort_spinner.adapter = adapter
        }
    }
}