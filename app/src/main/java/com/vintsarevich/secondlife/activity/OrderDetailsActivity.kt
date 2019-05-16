package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.Details
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.order_details_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_details_activity)
        val orderId = intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long
        order_details_cancel.setOnClickListener {
            startActivity(
                Intent(this, OrdersActivity::class.java)
            )
        }
        NetworkService.instance.getOrderServiceApi().getDetails(orderId)
            .enqueue(object : Callback<Details> {
                override fun onResponse(call: Call<Details>?, response: Response<Details>?) {
                    val details = response?.body()!!
                    order_name_result.text = "Order name: " + details.orderName
                    doctor_name_result.text = "Doctor name: " + details.doctorName
                    lab_name_result.text = "Lab name: " + details.labName
                    patient_name_result.text = "Patient name: " + details.patientName
                    disease_result.text = "Disease name: " + details.disease
                    test_recommendation_result.text =
                        "Test Recommendation name: " + details.testRecommendation
                    therapy_result.text = "Therapy name: " + details.therapy
                }

                override fun onFailure(call: Call<Details>?, t: Throwable?) {
                }
            })
    }
}
