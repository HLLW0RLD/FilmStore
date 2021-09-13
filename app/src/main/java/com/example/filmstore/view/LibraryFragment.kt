package com.example.filmstore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filmstore.R
import com.example.filmstore.databinding.FragmentLibraryBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.model.Film
import com.example.filmstore.view.Adapter.AdapterLibrary
import com.example.filmstore.viewmodel.LibraryViewModel
import com.example.filmstore.viewmodel.MainViewModel

class LibraryFragment: Fragment() {

    private var _binding: FragmentLibraryBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LibraryViewModel by lazy {
      ViewModelProvider(this).get(LibraryViewModel::class.java)
   }

    private val adapter: AdapterLibrary by lazy {
        AdapterLibrary()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            LibraryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerViewLibrary.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }

        viewModel.getAllHistory()

        adapter.setOnItemViewClickListener { film -> showDetails(film) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
            _binding = null
        }

    private fun renderData(state: AppState?) {
        when (state) {
            is AppState.SuccessOnList -> {
                adapter.setData(state.listFilms)
            }
        }
    }
}

