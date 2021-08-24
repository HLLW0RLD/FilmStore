package com.example.filmstore.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmstore.databinding.ItemFilmBinding
import com.example.filmstore.model.Film

class AdapterLibrary : RecyclerView.Adapter<AdapterLibrary.LibraryViewHolder>() {

    private var data: List<Film> = arrayListOf()

    fun setData(data: List<Film>) {
        this.data = data
        notifyDataSetChanged()
    }

    private var onItemViewClickListener: (Film) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Film) -> Unit){
        this.onItemViewClickListener = onItemViewClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterLibrary.LibraryViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterLibrary.LibraryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class LibraryViewHolder(private val binding: ItemFilmBinding) :
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
                    onItemViewClickListener(film)
                }
            }
        }
    }
}