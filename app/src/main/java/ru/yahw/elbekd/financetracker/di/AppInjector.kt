package ru.yahw.elbekd.financetracker.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import ru.yahw.elbekd.financetracker.App
import ru.yahw.elbekd.financetracker.Injectable

/**
 * Created by Elbek D. on 24.07.2018.
 */
object AppInjector {
    fun init(app: App) {
        DaggerAppComponent.builder().application(app).build().inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                if (activity is HasSupportFragmentInjector)
                    AndroidInjection.inject(activity)
                if (activity is FragmentActivity) {
                    activity.supportFragmentManager
                            .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                                    super.onFragmentCreated(fm, f, savedInstanceState)
                                    if (f is Injectable)
                                        AndroidSupportInjection.inject(f)
                                }
                            }, true)
                }
            }

        })
    }
}