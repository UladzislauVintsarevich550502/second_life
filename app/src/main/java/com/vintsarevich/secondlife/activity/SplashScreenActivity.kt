package com.vintsarevich.secondlife.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vintsarevich.secondlife.R
import com.vintsarevich.secondlife.model.User
import com.vintsarevich.secondlife.service.NetworkService
import kotlinx.android.synthetic.main.splash_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashScreenActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        splash_screen.setOnClickListener {
            val user = getNicknameAndPasswordFromSharedPreference()
            if (user!!.username != null && user.password != null) {
                NetworkService.instance.getDoctorServiceApi().checkUser(user)
                    .enqueue(object : Callback<Int> {
                        override fun onResponse(call: Call<Int>?, response: Response<Int>?) {
                            if (response!!.body() == 2) {
                                openMainActivity()
                            } else {
                                openLoginActivity()
                            }
                        }

                        override fun onFailure(call: Call<Int>?, t: Throwable?) {
                            openLoginActivity()
                        }
                    })
            } else {
                openLoginActivity()
            }
        }
    }

    private fun openMainActivity() {
        val mainIntent = Intent(this, OrdersActivity::class.java)
        this.startActivity(mainIntent)
        this.finish()
    }

    private fun openLoginActivity() {
        val mainIntent = Intent(this, LoginActivity::class.java)
        this.startActivity(mainIntent)
        this.finish()
    }

    private fun getNicknameAndPasswordFromSharedPreference(): User? {
        val settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return User(
            settings.getString(APP_PREFERENCES_USERNAME, null),
            settings.getString(APP_PREFERENCES_PASSWORD, null)
        )
    }

    companion object {
        const val APP_PREFERENCES = "settings"
        const val APP_PREFERENCES_USERNAME = "Username"
        const val APP_PREFERENCES_PASSWORD = "Password"
    }
}