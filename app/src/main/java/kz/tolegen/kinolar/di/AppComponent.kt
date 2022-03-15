package kz.tolegen.kinolar.di

import dagger.Component
import kz.tolegen.kinolar.di.module.NavigationModule
import kz.tolegen.kinolar.ui.root.RootView
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NavigationModule::class
    ]
)
interface AppComponent {
    fun inject(activity: RootView)
}