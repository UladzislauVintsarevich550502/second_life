package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.adapter.DiseaseStageAdapter
import com.vintsarevich.secondlife.model.DiseaseStage
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.choose_disease_stage_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseDiseaseStageActivity : AppCompatActivity() {

    lateinit var diseaseStageAdapter: DiseaseStageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_disease_stage_activity)

        setSupportActionBar(disease_stage_activity_toolbar)

        val orderId =
            intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        NetworkService.instance.getDiseaseStageServiceApi().getDiseaseStages(orderId)
            .enqueue(object : Callback<List<DiseaseStage>> {
                override fun onResponse(
                    call: Call<List<DiseaseStage>>?,
                    response: Response<List<DiseaseStage>>?
                ) {
                    diseaseStageAdapter =
                        DiseaseStageAdapter(
                            applicationContext,
                            response!!.body()!!
                        )
                    disease_stage_list_view.adapter = diseaseStageAdapter
                    disease_stage_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            NetworkService.instance.getOrderServiceApi()
                                .addDiseaseStageToOrder(id, orderId)
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

                override fun onFailure(call: Call<List<DiseaseStage>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.stage_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        startActivity(intent)
    }

}