package com.vintsarevich.secondlife.fragmen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vintsarevich.secondlife.NavigationHost
import com.vintsarevich.secondlife.R
import kotlinx.android.synthetic.main.shr_login_fragment.*
import kotlinx.android.synthetic.main.shr_login_fragment.view.*

/**
 * Fragment representing the login screen for SecondLife.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)
        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text)) {
                password_text_input.error = getString(R.string.shr_error_password)
            } else {
                password_text_input.error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }

        view.password_edit_text.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text.text)) {
                password_text_input.error = null
            }
            false
        }
        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
