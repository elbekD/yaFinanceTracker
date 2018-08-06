package ru.yahw.elbekd.financetracker.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.yahw.elbekd.financetracker.ui.about.AboutFragment
import ru.yahw.elbekd.financetracker.ui.main.MainFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletCardFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.NewWalletDialogFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.PeriodicOperationFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionDialogFragment

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindBalanceFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindAboutFragment(): AboutFragment

    @ContributesAndroidInjector
    abstract fun bindWalletCardFragment(): WalletCardFragment

    @ContributesAndroidInjector
    abstract fun bindTransactionDialogFragment(): TransactionDialogFragment

    @ContributesAndroidInjector
    abstract fun bindNewWalletDialogFragment(): NewWalletDialogFragment

    @ContributesAndroidInjector
    abstract fun bindPeriodicOperationFragment(): PeriodicOperationFragment
}