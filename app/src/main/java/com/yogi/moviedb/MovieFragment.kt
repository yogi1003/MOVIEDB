package com.yogi.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.moviedb.MovieAdapter
import com.yogi.moviedb.model.Movie
import com.yogi.moviedb.model.MovieResponse
import com.yogi.moviedb.service.MovieApiInterface
import com.yogi.moviedb.service.MovieApiService
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {
        private val movies = arrayListOf<Movie>()
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_movie, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            rv_movie.layoutManager = LinearLayoutManager(this.context)
            rv_movie.setHasFixedSize(true)
            getMovieData{movies: List<Movie> ->
                rv_movie.adapter = MovieAdapter(movies)
            }
            showRecycleView()
        }

        private fun getMovieData(callback: (List<Movie>) -> Unit) {
            val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
            apiService.getMovieList().enqueue(object: Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t:Throwable){
                }
                override fun onResponse(call: Call<MovieResponse>,response:Response<MovieResponse>){
                    return callback(response.body()!!.movies)
                }
            })
        }

        private fun showRecycleView(){
            rv_movie.layoutManager = LinearLayoutManager(this.context)
            rv_movie.adapter = MovieAdapter(movies)
        }
    }