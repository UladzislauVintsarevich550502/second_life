package com.vintsarevich.secondlife.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.order_start_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderStartActivity : AppCompatActivity() {

    var orderId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_start_activity)

        save_order_name.setOnClickListener {
            if (new_order_name.text == null) {
                new_order_name.error = getString(R.string.not_null_error)
            } else {
                NetworkService.instance.getOrderServiceApi()
                    .createOrder(new_order_name.text.toString(), getNicknameFromSharedPreference())
                    .enqueue(object : Callback<Long> {
                        override fun onFailure(call: Call<Long>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(call: Call<Long>, response: Response<Long>) {
                            if (response.body()!! != -1L) {
                                orderId = response.body()!!
                                changeActivity(orderId)
                            } else {
                                new_order_name.error = getString(R.string.order_name_already_exists)
                            }
                        }
                    })
            }
        }
    }

    private fun getNicknameFromSharedPreference(): String {
        val settings = getSharedPreferences(
            SplashScreenActivity.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        return settings.getString(SplashScreenActivity.APP_PREFERENCES_USERNAME, null)!!
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, ChooseDiseaseActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        startActivity(intent)
    }
}