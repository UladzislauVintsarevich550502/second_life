package com.vintsarevich.secondlife.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vintsarevich.secondlife.R
import kotlinx.android.synthetic.main.order_details_activity.*

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_details_activity)
        order_id.text = (intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long).toString()
    }
}
