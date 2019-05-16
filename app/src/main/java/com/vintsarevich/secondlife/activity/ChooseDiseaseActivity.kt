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
import com.vintsarevich.secondlife.adapter.DiseaseAdapter
import com.vintsarevich.secondlife.model.Disease
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.choose_disease_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseDiseaseActivity : AppCompatActivity() {

    lateinit var diseaseAdapter: DiseaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_disease_activity)

        setSupportActionBar(disease_activity_toolbar)

        val orderId = intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        NetworkService.instance.getDiseaseServiceApi().getAllDisease()
            .enqueue(object : Callback<List<Disease>> {
                override fun onResponse(
                    call: Call<List<Disease>>?,
                    response: Response<List<Disease>>?
                ) {
                    diseaseAdapter =
                        DiseaseAdapter(
                            applicationContext,
                            response!!.body()!!,
                            response.body()!!
                        )
                    disease_list_view.adapter = diseaseAdapter
                    disease_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            disease_list_view.isEnabled = false
                            NetworkService.instance.getOrderServiceApi()
                                .addDiseaseToOrder(orderId, id)
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

                override fun onFailure(call: Call<List<Disease>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.disease_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.disease_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = 1000
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                diseaseAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                diseaseAdapter.filter.filter(newText)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, ChooseDiseaseStageActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        disease_list_view.isEnabled = true
        startActivity(intent)
    }
}