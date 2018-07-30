package ru.yahw.elbekd.financetracker.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.yahw.elbekd.financetracker.MainActivity

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}