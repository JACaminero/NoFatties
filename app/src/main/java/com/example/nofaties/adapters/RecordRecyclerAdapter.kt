package com.example.nofaties.adapters

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nofaties.R
import com.example.nofaties.models.Record
import kotlinx.android.synthetic.main.record_row_item.view.*

class RecordRecyclerAdapter(var recordDataset: List<Record>) :
    RecyclerView.Adapter<RecordRecyclerAdapter.RecordViewHolder>() {

    class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecordViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.record_row_item, viewGroup, false)

        return RecordViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecordViewHolder, position: Int) {

        viewHolder.itemView.tv_recycler_date.text = recordDataset[position].recordDate.toString()
        viewHolder.itemView.tv_recycler_weight.text = recordDataset[position].recordWeight.toString()

//        viewHolder.itemView.setOnClickListener { view ->
//            onClickListener(recordDataset[position])
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recordDataset.size
}

