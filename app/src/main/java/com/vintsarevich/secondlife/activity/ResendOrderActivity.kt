package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.order_details_activity.*
import kotlinx.android.synthetic.main.resend_order_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResendOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resend_order_activity)
        val orderId = intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long
        order_details_cancel.setOnClickListener {
            startActivity(
                Intent(this, OrdersActivity::class.java)
            )
        }
        resend_order.setOnClickListener {
            NetworkService.instance.getFileApi().resendOrder(orderId)
                .enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                        val result = response?.body()!!
                        if (result) {
                            changeActivity(orderId)
                        } else {
                            changeAcivity()
                        }
                    }

                    override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                    }
                })
        }
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        startActivity(intent)
    }

    private fun changeAcivity() {
        startActivity(Intent(this, OrdersActivity::class.java))
    }
}
