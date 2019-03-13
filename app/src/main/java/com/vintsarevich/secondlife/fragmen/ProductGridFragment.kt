package com.vintsarevich.secondlife.fragmen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.vintsarevich.secondlife.R
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductGridFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater!!.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
}
