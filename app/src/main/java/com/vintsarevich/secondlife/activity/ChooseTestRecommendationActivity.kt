package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.adapter.TestRecommendationAdapter
import com.vintsarevich.secondlife.model.TestRecommendation
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.choose_test_recommendation_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseTestRecommendationActivity : AppCompatActivity() {

    lateinit var testRecommendationAdapter: TestRecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_test_recommendation_activity)

        setSupportActionBar(test_recommendation_activity_toolbar)

        val orderId =
            intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        NetworkService.instance.getTestRecommendationServiceApi().getTestRecommendations(orderId)
            .enqueue(object : Callback<List<TestRecommendation>> {
                override fun onResponse(
                    call: Call<List<TestRecommendation>>?,
                    response: Response<List<TestRecommendation>>?
                ) {
                    testRecommendationAdapter =
                        TestRecommendationAdapter(
                            applicationContext,
                            response!!.body()!!
                        )
                    test_recommendation_list_view.adapter = testRecommendationAdapter
                    test_recommendation_list_view.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, _, id ->
                            test_recommendation_list_view.isEnabled = false
                            NetworkService.instance.getOrderServiceApi()
                                .addTestRecommendationToOrder(orderId, id)
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

                override fun onFailure(call: Call<List<TestRecommendation>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.test_recommendation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, ChooseLabActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        test_recommendation_list_view.isEnabled = true
        startActivity(intent)
    }

}