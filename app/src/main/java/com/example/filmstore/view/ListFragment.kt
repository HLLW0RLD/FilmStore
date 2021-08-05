package com.example.filmstore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmstore.R
import com.example.filmstore.databinding.FragmentListBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.viewmodel.MainViewModel

class ListFragment : Fragment() {
    
    companion object{
        fun newInstance() = ListFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: FragmentListBinding? = null
    private val binding
                get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val observer = Observer<AppState>{renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observer)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(data: AppState) {

        when(data) {
            is AppState.Success -> {
                val filmData = data.filmData
                binding.loadingLayout.visability = view.GONE
            }
        }
    }
}