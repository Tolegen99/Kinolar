package kz.tolegen.kinolar


import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kz.tolegen.kinolar.app.moviedetail.MovieDetailView
import kz.tolegen.kinolar.app.movielist.MovieListView
import kz.tolegen.kinolar.app.root.RootView

object Screens {

    fun root() = ActivityScreen {
        Intent(it, RootView::class.java)
    }

    fun movieList() = FragmentScreen {
        MovieListView()
    }

    fun movieDetail(movieId: Long) = FragmentScreen {
        MovieDetailView.create(movieId)
    }
}