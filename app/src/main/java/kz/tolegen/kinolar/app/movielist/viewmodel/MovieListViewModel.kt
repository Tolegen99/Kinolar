package kz.tolegen.kinolar.app.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.data.models.classes.response.MovieListResp
import kz.tolegen.kinolar.data.models.enums.MovieListType
import kz.tolegen.kinolar.server.Api
import javax.inject.Inject

class MovieListViewModel : ViewModel() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var router: Router

    init {
        App.INSTANCE.appComponent.inject(this)
    }

    private val _movies = MutableLiveData<MovieListResp>()
    val movies: LiveData<MovieListResp> = _movies

    fun getMovies(movieListType: MovieListType) = viewModelScope.launch {
        val response = api.getMovies(movieListType.name.lowercase())
        if (response.isSuccessful)
            _movies.value = response.body()
    }
}