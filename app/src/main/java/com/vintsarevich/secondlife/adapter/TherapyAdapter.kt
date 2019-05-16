package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.Therapy

class TherapyAdapter(context: Context, private var therapies: List<Therapy>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return therapies[position]
    }

    override fun getItemId(position: Int): Long {
        return therapies[position].id
    }

    override fun getCount(): Int {
        return therapies.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = with(inflater) { inflate(R.layout.therapy_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.therapy_name).text = therapies[position].name
        return rowView
    }
}