package com.vintsarevich.secondlife.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.activity.SplashScreenActivity.Companion.APP_PREFERENCES
import com.vintsarevich.secondlife.activity.SplashScreenActivity.Companion.APP_PREFERENCES_PASSWORD
import com.vintsarevich.secondlife.activity.SplashScreenActivity.Companion.APP_PREFERENCES_USERNAME
import com.vintsarevich.secondlife.model.User
import com.vintsarevich.secondlife.service.NetworkService
import isPasswordValid
import isUsernameValid
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        next_button.setOnClickListener { onNextButtonClick() }
        cancel_button.setOnClickListener { cancel() }
    }

    private fun cancel() {
        startActivity(Intent(this, SplashScreenActivity::class.java))
        this.finish()
    }

    private fun onNextButtonClick() {
        password_input.error =
            if (!isPasswordValid(password_input.text)) getString(R.string.password_error) else null
        username_input.error =
            if (!isUsernameValid(username_input.text)) getString(R.string.not_null_error) else null
        if (isUsernameValid(username_input.text) && isPasswordValid(password_input.text)) {
            sendRequestForCheckUser()
        }
    }

    private fun sendRequestForCheckUser() {
        val user = User(username_input.text.toString(), password_input.text.toString())
        NetworkService.instance.getDoctorServiceApi().checkUser(user)
            .enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>?, response: Response<Int>?) {
                    if (response!!.body() == 1) {
                        error_text.text = getString(R.string.username_not_fount_error)
                    }
                    if (response.body() == 2) {
                        saveNicknameAndPasswordToSharedPreference(user)
                        changeActivity()
                    }
                    if (response.body() == 3) {
                        error_text.text = getString(R.string.incorrect_password_error)
                    }
                }

                override fun onFailure(call: Call<Int>?, t: Throwable?) {
                    error_text.text = t!!.message
                }
            })
    }

    private fun saveNicknameAndPasswordToSharedPreference(user: User) {
        val settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        settings.edit()
            .putString(APP_PREFERENCES_USERNAME, user.username)
            .putString(APP_PREFERENCES_PASSWORD, user.password)
            .apply()
    }

    private fun changeActivity() {
        startActivity(Intent(this, OrdersActivity::class.java))
        this.finish()
    }
}