package com.theapplication.apitest.viewmodels

import com.theapplication.apitest.models.Details
import com.theapplication.apitest.models.MoviesList
import com.theapplication.apitest.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMoviesList(page:Int):Response<MoviesList>{
        return RetrofitInstance.api.getMovies(page)
    }
    suspend fun getdetailsbyId(id:Int): Response<Details>{
        return RetrofitInstance.api.getDetailsById(id)
    }
}