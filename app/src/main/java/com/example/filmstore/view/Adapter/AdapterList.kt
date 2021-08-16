package com.example.filmstore.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.ItemFilmBinding
import com.example.filmstore.model.Film
import com.example.filmstore.model.repository.RepositoryImpl


class AdapterList : RecyclerView.Adapter<AdapterList.MainViewHolder>(){

    private var filmData: List<Film> = RepositoryImpl().getAllFilms()

    private var onItemViewClickListener: (Film) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Film) -> Unit){
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

        Glide.with(holder.binding.icon)
            .load("https://image.tmdb.org/t/p/w500${filmData.get(position).poster_path}")
            .into(holder.binding.icon)
    }

    override fun getItemCount(): Int {
        return filmData.size
    }

    inner class MainViewHolder(val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.apply {
                name.text = film.title
                genre.text = film.genre
                date.text = film.release.toString()
                root.setOnClickListener {
                    onItemViewClickListener(film)
                }
            }
        }
    }
}