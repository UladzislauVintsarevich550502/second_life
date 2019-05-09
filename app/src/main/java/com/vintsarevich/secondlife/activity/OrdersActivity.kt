package com.vintsarevich.secondlife.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import android.widget.SearchView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.adapter.OrderAdapter
import com.vintsarevich.secondlife.model.Order
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.orders_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    lateinit var orderAdapter: OrderAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders_activity)

        setSupportActionBar(orders_activity_toolbar)

        start_new_order.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    OrderProcessorActivity::class.java
                )
            )
        }

        NetworkService.instance.getOrderServiceApi().getAllOrders()
            .enqueue(object : Callback<List<Order>> {
                override fun onResponse(
                    call: Call<List<Order>>?,
                    response: Response<List<Order>>?
                ) {
                    orderAdapter =
                        OrderAdapter(applicationContext, response!!.body()!!, response.body()!!)
                    order_list_view.adapter = orderAdapter
                    order_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            changeActivity(id)
                        }
                }

                override fun onFailure(call: Call<List<Order>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.orders_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.order_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = 1000
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                orderAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                orderAdapter.filter.filter(newText)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(ORDER_ID_EXTRA, id)
        startActivity(intent)
    }

    companion object {
        const val ORDER_ID_EXTRA = "orderId"
    }
}