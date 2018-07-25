package ru.yahw.elbekd.financetracker.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.yahw.elbekd.financetracker.App
import ru.yahw.elbekd.financetracker.di.builder.ActivityBuilderModule
import javax.inject.Singleton

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}