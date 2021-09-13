package com.example.filmstore.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmstore.app.App
import com.example.filmstore.databinding.FragmentDetailsBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.model.Film
import com.example.filmstore.model.repository.LocalRepository
import com.example.filmstore.model.repository.LocalRepositoryImpl
import com.example.filmstore.viewmodel.MainViewModel
import java.time.format.DateTimeFormatter

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val filmLocalRepository: LocalRepository = LocalRepositoryImpl(App.getLibraryDao())

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var filmBundle: Film

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })

        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { viewModel.getFilmDetailFromRemote(it) }

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {

            filmLocalRepository.saveEntity(filmBundle)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun renderData(state: AppState) {
        when (state) {
            is AppState.SuccessOnFilm -> {
                val film = state.filmDetail
                val dateFormatter = DateTimeFormatter.ofPattern("d LLLL yyyy")
                binding.apply {
                    name.text = film.name
                    Glide
                        .with(root)
                        .load(film.posterPath)
                        .into(icon)
                    country.text = film.countries
                    year.text = "Премьера: ${film.releaseDate.format(dateFormatter)}"
                    genre.text = film.genres
                    overview.text = film.overview
                    budget.text = String.format("Бюджет: %d $", film.budget)
                    rating.text = String.format("Рейтинг: %.1f", film.voteAverage)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

