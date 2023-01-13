package com.example.multiselectioncallbackrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text=findViewById<TextView>(R.id.textView)

        val selectedItems = ArrayList<Int>()

        val recView = this.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = FlexboxLayoutManager(this)

        //............Defining Lambda Function...............
        recView.adapter = Adapter(populateData()) {
            if (selectedItems.contains(it)) {
                selectedItems.remove(it)
            } else {
                selectedItems.add(it)
            }
        }
        text.setOnClickListener {
            Log.d("waqar","${selectedItems.size}")
        }
    }

    private fun populateData(): ArrayList<String> {
        val data = ArrayList<String>()
        for (i in 0..20) {
            data.add("Waqar$i")
        }
        return data
    }
}