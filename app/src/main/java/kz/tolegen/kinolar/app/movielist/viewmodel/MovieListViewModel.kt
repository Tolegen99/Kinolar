package kz.tolegen.kinolar.app.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.Screens
import kz.tolegen.kinolar.data.enums.MovieListType
import kz.tolegen.kinolar.server.Api
import kz.tolegen.kinolar.server.response.MovieListResp
import javax.inject.Inject

class MovieListViewModel : ViewModel() {
    @Inject
    lateinit var api: Api

    @Inject
    lateinit var router: Router

    init {
        App.INSTANCE.appComponent.inject(this)
        getTopRatedMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    private val _topRatedMovies = MutableLiveData<MovieListResp>()
    val topRatedMovies: LiveData<MovieListResp> = _topRatedMovies

    private val _popularMovies = MutableLiveData<MovieListResp>()
    val popularMovies: LiveData<MovieListResp> = _popularMovies

    private val _upcomingMovies = MutableLiveData<MovieListResp>()
    val upcomingMovies: LiveData<MovieListResp> = _upcomingMovies

    fun getTopRatedMovies() =
        viewModelScope.launch {
            val response = api.getMovies(MovieListType.TOP_RATED.name.lowercase())
            if (response.isSuccessful)
                _topRatedMovies.value = response.body()
        }

    fun getPopularMovies() =
        viewModelScope.launch {
            val response = api.getMovies(MovieListType.POPULAR.name.lowercase())
            if (response.isSuccessful)
                _popularMovies.value = response.body()
        }

    fun getUpcomingMovies() =
        viewModelScope.launch {
            val response = api.getMovies(MovieListType.UPCOMING.name.lowercase())
            if (response.isSuccessful)
                _upcomingMovies.value = response.body()
        }

    fun openMovieDetailScreen(movieId: Long) =
        router.navigateTo(Screens.movieDetail(movieId))
}