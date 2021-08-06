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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmstore.R
import com.example.filmstore.databinding.FragmentListBinding
import com.example.filmstore.model.AppState
import com.example.filmstore.model.Film
import com.example.filmstore.view.Adapter.AdapterList
import com.example.filmstore.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_film.*

class ListFragment : Fragment() {
    
    companion object{
        fun newInstance() = ListFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter = AdapterList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        _binding!!.RecyclerViewList.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val observer = Observer<AppState>{renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.RecyclerViewList.adapter = adapter

        adapter.setOnItemViewClickListener { film ->
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(data: AppState) {

        when(data) {
            is AppState.Success -> {
                val filmData = data.filmData
            }
        }
    }

    private fun setData(filmData: Film){
        with(binding){
            name.text = filmData.name
            rating.text = filmData.rating.toString()
        }
    }
}