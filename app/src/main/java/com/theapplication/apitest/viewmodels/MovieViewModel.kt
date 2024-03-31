package com.theapplication.apitest.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapplication.apitest.models.Data
import com.theapplication.apitest.models.Details
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel:ViewModel() {
    private val repository=Repository()
    var state by mutableStateOf(ScreenState())
    var id by mutableStateOf(0)
    init {
        viewModelScope.launch {
           val response= repository.getMoviesList(state.page)
            state=state.copy(
                movies = response.body()!!.data
            )
        }
    }
    fun getDetailsById(){
        viewModelScope.launch {
            try {
                val response = repository.getdetailsbyId(id)
                if (response.isSuccessful){
                    state=state.copy(detailsData = response.body()!!)
                }
            }catch (e:Exception){

            }
        }
    }
}

data class ScreenState(
    val movies:List<Data> = emptyList(),
    val page:Int=1,
    val detailsData:Details=Details()
)