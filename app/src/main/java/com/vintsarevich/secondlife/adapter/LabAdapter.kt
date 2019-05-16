package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.Lab

class LabAdapter(context: Context, private var labs: List<Lab>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return labs[position]
    }

    override fun getItemId(position: Int): Long {
        return labs[position].id
    }

    override fun getCount(): Int {
        return labs.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = with(inflater) { inflate(R.layout.lab_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.lab_name).text = labs[position].name
        return rowView
    }
}