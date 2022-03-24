package kz.tolegen.kinolar.di

import dagger.Component
import kz.tolegen.kinolar.di.module.NavigationModule
import kz.tolegen.kinolar.di.module.NetworkModule
import kz.tolegen.kinolar.ui.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.ui.root.RootView
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class]
)
interface AppComponent {
    fun inject(activity: RootView)


    fun inject(viewModel: MovieListViewModel)

}