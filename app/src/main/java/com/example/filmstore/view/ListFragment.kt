package com.example.filmstore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmstore.R
import com.example.filmstore.databinding.FragmentListBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.model.DTO.GenreDTO
import com.example.filmstore.model.Film
import com.example.filmstore.view.Adapter.AdapterList
import com.example.filmstore.viewmodel.MainViewModel
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var genresList: List<GenreDTO> = listOf()

    private val adaptersByGenre: MutableList<AdapterList> = mutableListOf()

    private val titlesGenre: MutableList<MaterialTextView> = mutableListOf()

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter = AdapterList()

    interface OnFilmClickListener {
        fun onFilmClick(film: Film)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getListFilmFromRemote(null)

        binding.RecyclerViewBest.adapter = adapter

    }

    private val filmClickListener = object : OnFilmClickListener {
        override fun onFilmClick(film: Film) {
            val manager = parentFragmentManager
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
            manager
                .beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack(DetailsFragment.BUNDLE_EXTRA)
                .commit()
        }
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.SuccessOnListByGenre -> {
                for (genre in genresList) {
                    if (genre.id == state.genreID) {
                        val index = genresList.indexOf(genre)
                        adaptersByGenre[index].setFilmData(state.listFilms)
                        adaptersByGenre[index].setFilmListener(filmClickListener)
                        if (genre != genresList.last()) {
                            viewModel.getListFilmFromRemote(genresList[index + 1].id.toString())
                        }
                    }
                }
            }
            is AppState.SuccessOnList -> {
                adapter.setFilmData(state.listFilms)
                adapter.setFilmListener(filmClickListener)
                binding.RecyclerViewBest.adapter = adapter
                binding.RecyclerViewBest.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                viewModel.getGenresListFromRemote()
            }
            is AppState.SuccessOnGenres -> {
                for (genre in state.genres) {
                    genresList = state.genres
                    val index = state.genres.indexOf(genre)
                    titlesGenre.add(
                        MaterialTextView(binding.listsContainer.context).apply {
                            text = genre.name?.replaceFirstChar { it.uppercase() }
                            layoutParams = binding.listsContainer.layoutParams
                        }
                    )
                    adaptersByGenre.add(
                        AdapterList()
                    )
                    with(binding.listsContainer) {
                        addView(titlesGenre[index])
                        addView(RecyclerView(context).apply {
                            adapter = adaptersByGenre[index]
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        })
                    }
                }
                viewModel.getListFilmFromRemote(state.genres.first().id.toString())
            }
            is AppState.Error -> {
                Toast.makeText(
                    context,
                    "Ошибка загрузки данных. Попробуем ещё раз",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.getListFilmFromRemote(null)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        adapter::removeListener
        for (adapter in adaptersByGenre) {
            adapter::removeListener
        }
        binding.listsContainer.removeAllViews()
        _binding = null
    }
}