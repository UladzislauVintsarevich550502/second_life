package com.vintsarevich.secondlife.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.PatientInfo
import com.vintsarevich.secondlife.service.NetworkService
import isNotEmpty
import kotlinx.android.synthetic.main.choose_therapy_activity.*
import kotlinx.android.synthetic.main.patient_info_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PatientInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_info_activity)
        val c = Calendar.getInstance()
        day_of_birthday_input.maxDate = c.timeInMillis
        c.set(1910, 1, 1)
        day_of_birthday_input.minDate = c.timeInMillis

        setSupportActionBar(therapy_activity_toolbar)

        val orderId = intent!!.extras!!.get(OrdersActivity.ORDER_ID_EXTRA) as Long

        submit_order.setOnClickListener { submitOrder(orderId) }

    }

    private fun submitOrder(orderId: Long) {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        if (checkFieldValues()) {
            val patientInfo = PatientInfo(
                formatter.format(
                    Date(
                        day_of_birthday_input.year - 1900,
                        day_of_birthday_input.month,
                        day_of_birthday_input.dayOfMonth
                    )
                ), gender_input.selectedItem.toString(),
                first_name_input.text.toString(),
                second_name_input.text.toString(),
                address_input.text.toString()
            )
            NetworkService.instance.getOrderServiceApi().submitOrder(orderId, patientInfo)
                .enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                        if (response?.body() != null && response.body()!!) {
                            changeActivity(orderId)
                        } else {
                            changeActivity(orderId)
                        }
                    }

                    override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
        }
    }

    private fun checkFieldValues(): Boolean {
        first_name_input.error =
            if (!isNotEmpty(first_name_input.text)) getString(R.string.not_null_error) else null
        second_name_input.error =
            if (!isNotEmpty(second_name_input.text)) getString(R.string.not_null_error) else null
        address_input.error =
            if (!isNotEmpty(address_input.text)) getString(R.string.not_null_error) else null
        return isNotEmpty(first_name_input.text, second_name_input.text, address_input.text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.patient_info_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActivity(id: Long) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(OrdersActivity.ORDER_ID_EXTRA, id)
        startActivity(intent)
    }
}