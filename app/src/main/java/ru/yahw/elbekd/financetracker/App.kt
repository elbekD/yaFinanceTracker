package ru.yahw.elbekd.financetracker

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ru.yahw.elbekd.financetracker.di.AppInjector
import javax.inject.Inject

/**
 * Created by Elbek D. on 20.07.2018.
 */
class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}