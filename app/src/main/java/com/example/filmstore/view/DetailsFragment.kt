package com.example.filmstore.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.FragmentDetailsBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.model.Film
import com.example.filmstore.viewmodel.DetailsViewModel

private const val IMAGE_URL = "http://image.tmdb.org/t/p/w500/"

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
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

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmBundle = arguments?.getParcelable<Film>(BUNDLE_EXTRA)!!

        viewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }

        viewModel.getFilmFromRemoteSource(filmBundle.id)

        binding.add.setOnClickListener {

            saveFilm(filmBundle)
        }
    }

    private fun saveFilm(
        film : Film
    ) {
        viewModel.saveFilmToDB(
            Film(
                film.id,
                film.name,
                film.posterPath,
                film.overview,
                film.year,
                film.country
            )
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                setFilm(appState.filmData[0])
            }
        }
    }

    private fun setFilm(film: Film) {
        with(binding) {
            filmBundle.let { film ->
                name.text = film.name

                Glide
                    .with(binding.root)
                    .load(IMAGE_URL + filmBundle.posterPath)
                    .into(binding.iconDetails)
            }
        }
    }
}