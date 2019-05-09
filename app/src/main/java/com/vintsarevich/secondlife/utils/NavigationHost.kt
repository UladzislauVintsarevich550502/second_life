package com.vintsarevich.secondlife.utils

import android.support.v4.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean)
}
