package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.adapter.TherapyAdapter
import com.vintsarevich.secondlife.model.Therapy
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.choose_therapy_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseTherapyActivity : AppCompatActivity() {

    lateinit var therapyAdapter: TherapyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_therapy_activity)

        setSupportActionBar(therapy_activity_toolbar)

        val orderId =
            intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        NetworkService.instance.getTherapyApi().getTherapies(orderId)
            .enqueue(object : Callback<List<Therapy>> {
                override fun onResponse(
                    call: Call<List<Therapy>>?,
                    response: Response<List<Therapy>>?
                ) {
                    therapyAdapter = TherapyAdapter(applicationContext, response!!.body()!!)
                    therapy_list_view.adapter = therapyAdapter
                    therapy_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            therapy_list_view.isEnabled = false
                            NetworkService.instance.getOrderServiceApi()
                                .addTherapyToOrder(orderId, id)
                                .enqueue(object : Callback<Void> {
                                    override fun onResponse(
                                        call: Call<Void>?,
                                        response: Response<Void>?
                                    ) {
                                        changeActivity(orderId)
                                    }

                                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }
                                })
                        }
                }

                override fun onFailure(call: Call<List<Therapy>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.therapy_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, ChooseTestRecommendationActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        therapy_list_view.isEnabled = true
        startActivity(intent)
    }

}