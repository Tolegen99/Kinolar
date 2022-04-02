package kz.tolegen.kinolar.app.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.data.models.classes.response.MovieResp
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

    private val _movies = MutableLiveData<List<MovieResp>>()
    val movies: LiveData<List<MovieResp>> = _movies

    private var _isNextPageLoading = false
    val isNextPageLoading = _isNextPageLoading

    private var currentPage = 1

    fun getMovies(movieListType: MovieListType) = viewModelScope.launch {
        val response = api.getMovies(movieListType.name.lowercase())
        if (response.isSuccessful) {
            _movies.value = response.body()?.movies
            currentPage = response.body()!!.page
        }
    }

    fun loadNextPage(movieListType: MovieListType) = viewModelScope.launch {
        _isNextPageLoading = true
        val response = api.getMovies(movieListType.name.lowercase(), ++currentPage)
        if (response.isSuccessful) {
            _isNextPageLoading = false
            _movies.value = mutableListOf<MovieResp>().apply {
                addAll(_movies.value!!)
                addAll(response.body()?.movies ?: emptyList())
            }
        } else {
            _isNextPageLoading = false
        }
    }

}