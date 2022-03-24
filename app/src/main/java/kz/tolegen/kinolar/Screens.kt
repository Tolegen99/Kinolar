package kz.tolegen.kinolar


import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kz.tolegen.kinolar.app.movielist.MovieListView
import kz.tolegen.kinolar.app.root.RootView

object Screens {

    fun Root() = ActivityScreen {
        Intent(it, RootView::class.java)
    }

    fun MovieList() = FragmentScreen {
        MovieListView()
    }
}