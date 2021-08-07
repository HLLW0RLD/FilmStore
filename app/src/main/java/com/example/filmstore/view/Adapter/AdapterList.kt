package com.example.filmstore.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmstore.databinding.ItemFilmBinding
import com.example.filmstore.model.Film
import com.example.filmstore.model.repository.RepositoryImpl

class AdapterList : RecyclerView.Adapter<AdapterList.MainViewHolder>(), SetOnItemViewClickListener {

    private var filmData: List<Film> = RepositoryImpl().getAllFilms()

    private var onItemViewClickListener: (Film) -> Unit = {}

    override fun setOnItemViewClickListener(onItemViewClickListener: (Film) -> Unit){
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun setFilm(data: List<Film>) {
        filmData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmData[position])
    }

    override fun getItemCount(): Int {
        return filmData.size
    }

    inner class MainViewHolder(val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.apply {
                name.text = film.name
                rating.text = film.rating.toString()
                root.setOnClickListener {
                    onItemViewClickListener(film)
                }
            }
        }
    }
}