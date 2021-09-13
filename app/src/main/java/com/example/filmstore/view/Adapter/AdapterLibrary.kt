package com.example.filmstore.view.Adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.ItemFilmBinding
import com.example.filmstore.model.Film
import com.example.filmstore.model.FilmDetail
import com.example.filmstore.view.ListFragment
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AdapterLibrary : RecyclerView.Adapter<AdapterLibrary.LibraryViewHolder>() {

    private var data: List<Film> = arrayListOf()

    private var onItemViewClickListener: (Film) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Film) -> Unit){
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun setData(filmData: List<Film>) {
        data = filmData
        notifyDataSetChanged()
    }



    inner class LibraryViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(film: Film) {
            with(binding) {
                name.text = film.name
                Glide
                    .with(root)
                    .load(film.posterPath)
                    .into(binding.icon)
                binding.apply {
                    name.text = film.name
                    date.text = film.year.toString()
                    root.setOnClickListener {
                        onItemViewClickListener(film)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LibraryViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
