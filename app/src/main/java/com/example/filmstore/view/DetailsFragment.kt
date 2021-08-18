package com.example.filmstore.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.FragmentDetailsBinding
import com.example.filmstore.model.Film

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var filmBundle: Film

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val filmBundle = arguments?.getParcelable<Film>(BUNDLE_EXTRA)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.name.text = filmBundle!!.name
        Glide
            .with(binding.root)
            .load(filmBundle.posterPath.toUri())
            .into(binding.iconDetails)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmBundle = arguments?.getParcelable<Film>(BUNDLE_EXTRA)!!

        displayFilm(filmBundle)
    }

    private fun displayFilm(film: Film) {
        with(binding) {
            filmBundle.also{ film ->
                name.text = film.name
                year.text = film.year.toString()
                country.text = film.country
                description.text = film.overview
            }
        }
    }
}