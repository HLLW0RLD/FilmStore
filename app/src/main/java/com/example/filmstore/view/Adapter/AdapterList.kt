package com.example.filmstore.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.ItemFilmBinding
import com.example.filmstore.model.Film
import com.example.filmstore.view.ListFragment


class AdapterList : RecyclerView.Adapter<AdapterList.MainViewHolder>(){

    private var filmData: List<Film> = listOf()

    private var onFilmClickListener: ListFragment.OnFilmClickListener? = null

    fun setFilmData(newFilmData: List<Film>) {
        this.filmData = newFilmData
        notifyDataSetChanged()
    }

    fun setFilmListener(onFilmClickListener: ListFragment.OnFilmClickListener?) {
        this.onFilmClickListener = onFilmClickListener
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
            Glide
                .with(binding.root)
                .load(film.posterPath.toUri())
                .into(binding.icon)

            binding.apply {
                name.text = film.name
                date.text = film.year.toString()
                root.setOnClickListener {
                    onFilmClickListener?.onFilmClick(film)
                }
            }
        }
    }
}