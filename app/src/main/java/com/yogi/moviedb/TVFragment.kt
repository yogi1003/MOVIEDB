package com.yogi.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.moviedb.model.TV
import com.yogi.moviedb.model.TVResponse
import com.yogi.moviedb.service.TVApiInterface
import com.yogi.moviedb.service.TVApiService
import kotlinx.android.synthetic.main.fragment_tv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVFragment : Fragment() {
    private val tv = arrayListOf<TV>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.setHasFixedSize(true)
        getTVData { tv : List<TV> ->
            rv_television.adapter = TVAdapter(tv)
        }
        showRecyclerView()
    }

    private fun getTVData(callback: (List<TV>) -> Unit){
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVList().enqueue(object : Callback<TVResponse> {
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                return callback(response.body()!!.television)
            }

        })
    }

    private fun showRecyclerView() {
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.adapter = TVAdapter(tv)
    }
}