package kz.tolegen.kinolar.di

import dagger.Component
import kz.tolegen.kinolar.app.moviedetail.viewmodel.MovieDetailViewModel
import kz.tolegen.kinolar.di.module.NavigationModule
import kz.tolegen.kinolar.di.module.NetworkModule
import kz.tolegen.kinolar.app.home.viewmodel.HomeViewModel
import kz.tolegen.kinolar.app.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.app.root.RootView
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class]
)
interface AppComponent {
    fun inject(activity: RootView)


    fun inject(viewModel: HomeViewModel)
    fun inject(viewModel: MovieListViewModel)
    fun inject(viewModel: MovieDetailViewModel)
}