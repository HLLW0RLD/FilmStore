package com.example.filmstore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.filmstore.R
import com.example.filmstore.model.Film


fun Fragment.showDetails(film: Film){

    activity?.supportFragmentManager?.apply {
        beginTransaction()
            .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
            }))
            .addToBackStack("")
            .commitAllowingStateLoss()
    }
}