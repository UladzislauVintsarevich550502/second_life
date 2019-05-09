package com.vintsarevich.secondlife.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.activity.SplashScreenActivity
import com.vintsarevich.secondlife.service.NetworkService
import com.vintsarevich.secondlife.utils.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderStartFragment : Fragment() {

    private lateinit var saveOrderNameButton: Button
    private lateinit var orderName: TextInputEditText
    var orderId: Long = -1L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.order_start_fragment, container, false)
        saveOrderNameButton = view.findViewById(R.id.save_order_name)
        orderName = view.findViewById(R.id.new_order_name)
        saveOrderNameButton.setOnClickListener {
            if (orderName.text == null) {
                orderName.error = getString(R.string.not_null_error)
            } else {
                NetworkService.instance.getOrderServiceApi()
                    .createOrder(orderName.text.toString(), getNicknameFromSharedPreference())
                    .enqueue(object : Callback<Long> {
                        override fun onFailure(call: Call<Long>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(call: Call<Long>, response: Response<Long>) {
                            if (response.body()!! != -1L) {
                                orderId = response.body()!!
                                (activity as NavigationHost).navigateTo(
                                    ChooseDiseaseFragment(),
                                    true
                                )
                            } else {
                                orderName.error = getString(R.string.order_name_already_exists)
                            }
                        }
                    })
            }
        }
        return view
    }

    private fun getNicknameFromSharedPreference(): String {
        val settings = activity!!.getSharedPreferences(
            SplashScreenActivity.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        return settings.getString(SplashScreenActivity.APP_PREFERENCES_USERNAME, null)!!
    }
}
