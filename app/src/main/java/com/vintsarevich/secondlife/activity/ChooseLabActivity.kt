package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.adapter.LabAdapter
import com.vintsarevich.secondlife.model.Lab
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.choose_lab_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseLabActivity : AppCompatActivity() {

    lateinit var labAdapter: LabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_lab_activity)

        setSupportActionBar(lab_activity_toolbar)

        val orderId =
            intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        NetworkService.instance.getLabServiceApi().getLabs(orderId)
            .enqueue(object : Callback<List<Lab>> {
                override fun onResponse(
                    call: Call<List<Lab>>?,
                    response: Response<List<Lab>>?
                ) {
                    labAdapter =
                        LabAdapter(
                            applicationContext,
                            response!!.body()!!
                        )
                    lab_list_view.adapter = labAdapter
                    lab_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            lab_list_view.isEnabled = false
                            NetworkService.instance.getOrderServiceApi()
                                .addLabToOrder(orderId, id)
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

                override fun onFailure(call: Call<List<Lab>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.lab_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, PatientInfoActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        lab_list_view.isEnabled = true
        startActivity(intent)
    }

}