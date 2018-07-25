package ru.yahw.elbekd.financetracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.yahw.elbekd.financetracker.ui.about.AboutViewModel
import ru.yahw.elbekd.financetracker.ui.balance.SharedViewModel
import ru.yahw.elbekd.financetracker.ui.main.MainViewModel
import javax.inject.Singleton

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
//    @Singleton
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutVM(aboutVM: AboutViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindBalanceVM(balanceVM: SharedViewModel): ViewModel

    @Binds
    @IntoMap
//    @Singleton
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainVM(mainVM: MainViewModel): ViewModel

    @Binds
//    @Singleton
    abstract fun bindVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}