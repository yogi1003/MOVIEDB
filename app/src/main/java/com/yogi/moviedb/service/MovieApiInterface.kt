package com.yogi.moviedb.service

import com.yogi.moviedb.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=d05a3dbf31a2128b7bc15971e5a43d40")
    fun getMovieList(): Call<MovieResponse>
}