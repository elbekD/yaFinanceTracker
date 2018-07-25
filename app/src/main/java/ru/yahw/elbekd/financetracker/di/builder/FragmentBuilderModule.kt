package ru.yahw.elbekd.financetracker.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.yahw.elbekd.financetracker.ui.about.AboutFragment
import ru.yahw.elbekd.financetracker.ui.balance.BalanceFragment
import ru.yahw.elbekd.financetracker.ui.balance.operation.OperationFragment

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindBalanceFragment(): BalanceFragment

    @ContributesAndroidInjector
    abstract fun bindAboutFragment(): AboutFragment

    @ContributesAndroidInjector
    abstract fun bindOperationFragment(): OperationFragment
}