package com.vintsarevich.secondlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.Order
import com.vintsarevich.secondlife.utils.OrderStatusProgress
import com.vintsarevich.secondlife.utils.OrderStatusProgressType

class OrderAdapter(
    context: Context, private var orders: List<Order>,
    private var filteredOrders: List<Order>
) : BaseAdapter(), Filterable {

    override fun getFilter(): Filter {
        return (object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val stringValue = constraint.toString()
                filteredOrders = if (stringValue.isEmpty()) {
                    orders
                } else {
                    orders.filter { order ->
                        order.name.toLowerCase().contains(stringValue.toLowerCase())
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredOrders
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredOrders = results.values as List<Order>
                notifyDataSetChanged()
            }

        })
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return filteredOrders[position]
    }

    override fun getItemId(position: Int): Long {
        return filteredOrders[position].id
    }

    override fun getCount(): Int {
        return filteredOrders.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = with(inflater) { inflate(R.layout.order_item_view, parent, false) }
        rowView.findViewById<TextView>(R.id.order_name).text = filteredOrders[position].name
        val orderStatusProgress = getOrderStatusProgress(filteredOrders[position].status)
        val progressBar = rowView.findViewById<CircularProgressBar>(R.id.order_status)
        progressBar.color = orderStatusProgress.color
        progressBar.setProgressWithAnimation(orderStatusProgress.percent, 1000)
        return rowView
    }

    private fun getOrderStatusProgress(status: String): OrderStatusProgress = when (status) {
        OrderStatusProgressType.ORDER_ARCHIVED.name -> OrderStatusProgressType.ORDER_ARCHIVED.orderStatusProgress
        OrderStatusProgressType.ORDER_COMPLETE.name -> OrderStatusProgressType.ORDER_COMPLETE.orderStatusProgress
        OrderStatusProgressType.ORDER_SUBMIT_TO_LAB.name -> OrderStatusProgressType.ORDER_SUBMIT_TO_LAB.orderStatusProgress
        OrderStatusProgressType.ORDER_START.name -> OrderStatusProgressType.ORDER_START.orderStatusProgress
        else -> OrderStatusProgressType.DEFAULT.orderStatusProgress
    }
}