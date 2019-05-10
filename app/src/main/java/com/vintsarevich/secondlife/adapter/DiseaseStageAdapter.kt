package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.DiseaseStage

class DiseaseStageAdapter(context: Context, private var diseaseStages: List<DiseaseStage>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return diseaseStages[position]
    }

    override fun getItemId(position: Int): Long {
        return diseaseStages[position].id
    }

    override fun getCount(): Int {
        return diseaseStages.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = with(inflater) { inflate(R.layout.disease_stage_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.disease_stage_name).text = diseaseStages[position].name
        return rowView
    }
}