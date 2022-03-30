package kz.tolegen.kinolar


import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kz.tolegen.kinolar.app.moviedetail.MovieDetailView
import kz.tolegen.kinolar.app.home.HomeView
import kz.tolegen.kinolar.app.movielist.MovieListView
import kz.tolegen.kinolar.app.root.RootView
import kz.tolegen.kinolar.data.models.enums.MovieListType

object Screens {

    fun root() = ActivityScreen {
        Intent(it, RootView::class.java)
    }

    fun home() = FragmentScreen {
        HomeView()
    }

    fun movieDetail(movieId: Long) = FragmentScreen {
        MovieDetailView.create(movieId)
    }

    fun movieList(movieListType: MovieListType) = FragmentScreen {
        MovieListView.create(movieListType)
    }
}