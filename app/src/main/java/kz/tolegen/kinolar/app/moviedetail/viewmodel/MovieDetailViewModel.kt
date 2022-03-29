package kz.tolegen.kinolar.app.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.data.models.classes.response.MovieDetailResp
import kz.tolegen.kinolar.server.Api
import javax.inject.Inject

class MovieDetailViewModel : ViewModel() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var router: Router

    init {
        App.INSTANCE.appComponent.inject(this)
    }

    private val _movieDetail = MutableLiveData<MovieDetailResp>()
    val movieDetail: LiveData<MovieDetailResp> = _movieDetail

    fun getMovieDetail(movieId: Long) =
        viewModelScope.launch {
            val responce = api.getMovieDetail(movieId)
            if (responce.isSuccessful)
                _movieDetail.value = responce.body()
        }

    fun onBackPressed() = router.exit()

}