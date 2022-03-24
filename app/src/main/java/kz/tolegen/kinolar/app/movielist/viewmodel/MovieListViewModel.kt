package kz.tolegen.kinolar.app.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.server.Api
import kz.tolegen.kinolar.server.response.MovieListResp
import javax.inject.Inject

class MovieListViewModel : ViewModel() {
    @Inject
    lateinit var api: Api

    init {
        App.INSTANCE.appComponent.inject(this)
    }

    private val _topRatedMovies = MutableLiveData<MovieListResp>()
    val topRatedMovies: LiveData<MovieListResp> = _topRatedMovies

    fun getTopRatedMovies() =
        viewModelScope.launch {
            val response = api.getTopRatedMovies()
            if (response.isSuccessful)
                _topRatedMovies.value = response.body()
        }
}