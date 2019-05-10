package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.Disease

class DiseaseAdapter(
    context: Context, private var diseases: List<Disease>,
    private var filteredDiseases: List<Disease>
) : BaseAdapter(), Filterable {

    override fun getFilter(): Filter {
        return (object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val stringValue = constraint.toString()
                filteredDiseases = if (stringValue.isEmpty()) {
                    diseases
                } else {
                    diseases.filter { disease ->
                        disease.name.toLowerCase().contains(stringValue.toLowerCase())
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredDiseases
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredDiseases = results.values as List<Disease>
                notifyDataSetChanged()
            }
        })
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return filteredDiseases[position]
    }

    override fun getItemId(position: Int): Long {
        return filteredDiseases[position].id
    }

    override fun getCount(): Int {
        return filteredDiseases.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = with(inflater) { inflate(R.layout.disease_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.disease_name).text = filteredDiseases[position].name
        return rowView
    }
}