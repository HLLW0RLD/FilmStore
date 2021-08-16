package com.example.filmstore.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.filmstore.databinding.FragmentDetailsBinding
import com.example.filmstore.model.DTO.FilmDTO
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
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmBundle = arguments?.getParcelable<Film>(BUNDLE_EXTRA)!!
        val loader = FilmLoader(onLoadListener, filmBundle.movie_id)
        loader.loadFilm()

    }

    private val onLoadListener = object: FilmLoader.FilmLoaderListener{
        override fun onLoaded(filmDTO: FilmDTO) {
            displayFilm(filmDTO)
        }

        override fun onFailed(throwable: Throwable) {
            // Обработка ошибок
        }
    }

    private fun displayFilm(filmDTO: FilmDTO) {
        with(binding) {
            filmBundle.also{ film ->
                name.text = film.title

            }

            filmDTO.fact?.let { fact ->
                name.text = fact.title
                genre.text = fact.genre
                year.text = fact.release
                description.text = fact.description
            }
        }
    }
}

