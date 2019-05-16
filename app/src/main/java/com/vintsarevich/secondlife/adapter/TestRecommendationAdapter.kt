package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.TestRecommendation

class TestRecommendationAdapter(
    context: Context,
    private var testRecommendations: List<TestRecommendation>
) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return testRecommendations[position]
    }

    override fun getItemId(position: Int): Long {
        return testRecommendations[position].id
    }

    override fun getCount(): Int {
        return testRecommendations.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView =
            with(inflater) { inflate(R.layout.test_recommendation_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.test_recommendation_name).text =
            testRecommendations[position].name
        return rowView
    }
}