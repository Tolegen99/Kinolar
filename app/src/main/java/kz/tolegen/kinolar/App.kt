package kz.tolegen.kinolar

import android.app.Application
import kz.tolegen.kinolar.di.AppComponent
import kz.tolegen.kinolar.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}