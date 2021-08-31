package com.example.filmstore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filmstore.databinding.FragmentLibraryBinding
import com.example.filmstore.view.Adapter.AdapterLibrary
import com.example.filmstore.viewmodel.MainViewModel

class LibraryFragment: Fragment() {

    private var _binding: FragmentLibraryBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
      ViewModelProvider(this).get(MainViewModel::class.java)
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
        viewModel.getLiveData().observe(viewLifecycleOwner) { //renderData(it) }
            

            adapter.setOnItemViewClickListener { film -> showDetails(film) }
        }
  }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

