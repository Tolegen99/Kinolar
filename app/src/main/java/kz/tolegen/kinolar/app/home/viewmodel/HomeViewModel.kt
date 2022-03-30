package kz.tolegen.kinolar.app.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.Screens
import kz.tolegen.kinolar.data.models.classes.response.MovieListResp
import kz.tolegen.kinolar.data.models.enums.MovieListType
import kz.tolegen.kinolar.server.Api
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var api: Api

    @Inject
    lateinit var router: Router

    init {
        App.INSTANCE.appComponent.inject(this)
        getMovies(MovieListType.TOP_RATED)
        getMovies(MovieListType.POPULAR)
        getMovies(MovieListType.UPCOMING)
    }

    private val _topRatedMovies = MutableLiveData<MovieListResp>()
    val topRatedMovies: LiveData<MovieListResp> = _topRatedMovies

    private val _popularMovies = MutableLiveData<MovieListResp>()
    val popularMovies: LiveData<MovieListResp> = _popularMovies

    private val _upcomingMovies = MutableLiveData<MovieListResp>()
    val upcomingMovies: LiveData<MovieListResp> = _upcomingMovies

    fun getMovies(movieListType: MovieListType) = viewModelScope.launch {
        val response = api.getMovies(movieListType.name.lowercase())
        if (response.isSuccessful) {
            when (movieListType) {
                MovieListType.TOP_RATED -> _topRatedMovies.value = response.body()
                MovieListType.POPULAR -> _popularMovies.value = response.body()
                MovieListType.UPCOMING -> _upcomingMovies.value = response.body()
            }
        }
    }

    fun navigateToMovieDetailScreen(movieId: Long) =
        router.navigateTo(Screens.movieDetail(movieId))

    fun navigateToMovieListScreen(movieListType: MovieListType) =
        router.navigateTo(Screens.movieList(movieListType))
}