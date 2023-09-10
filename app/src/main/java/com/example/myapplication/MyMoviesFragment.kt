package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MyMoviesFragment : Fragment(R.layout.fragment_my_movies) {

    private lateinit var myMoviesAdapter: MyMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myMovies = listOf(
            MyMovie("응애 1", "어흑 1"),
            MyMovie("끼얏 2", "호우 2"),
            MyMovie("몽 2", "파 2"),
            MyMovie("냥 2", "이 2"),
            MyMovie("탱 2", "어 2")
        )

        myMoviesAdapter = MyMoviesAdapter(myMovies)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_my_movies_list)
        recyclerView.adapter = myMoviesAdapter
    }
}
