package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.DefaultMyMovieRepository
import com.example.myapplication.data.MyMovie
import com.example.myapplication.databinding.FragmentMyMoviesBinding
import com.example.myapplication.databinding.ItemMyMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MyMoviesFragment : Fragment() {

    @Inject
    lateinit var dMMRepository: DefaultMyMovieRepository

    private var _fragmentMyMoviesBinding: FragmentMyMoviesBinding? = null
    private val fragmentMyMoviesBinding get() = _fragmentMyMoviesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMyMoviesBinding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return fragmentMyMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myMoviesAdapter = MyMoviesAdapter(MyMovieDiffCallback)
        fragmentMyMoviesBinding.rvMyMoviesList.adapter = myMoviesAdapter

        lifecycleScope.launch {
            dMMRepository.observeAll().collect { movies ->
                myMoviesAdapter.submitList(movies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMyMoviesBinding = null
    }

    companion object {
        val MyMovieDiffCallback = object : DiffUtil.ItemCallback<MyMovie>() {
            override fun areItemsTheSame(oldItem: MyMovie, newItem: MyMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyMovie, newItem: MyMovie): Boolean {
                return oldItem == newItem
            }
        }
    }

    // RecyclerView 어댑터
    private inner class MyMoviesAdapter(diffCallback: DiffUtil.ItemCallback<MyMovie>) :
        ListAdapter<MyMovie, MyMoviesAdapter.MyMovieViewHolder>(diffCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemMyMovieBinding = ItemMyMovieBinding.inflate(inflater, parent, false)
            return MyMovieViewHolder(itemMyMovieBinding)
        }

        override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        inner class MyMovieViewHolder(private val binding: ItemMyMovieBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: MyMovie) {
                binding.tvMovieTitle.text = movie.title
                binding.tvMovieDescription.text = movie.description
            }
        }
    }
}
