package ru.yahw.elbekd.financetracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.yahw.elbekd.financetracker.ui.about.AboutViewModel
import ru.yahw.elbekd.financetracker.ui.main.MainFragmentViewModel
import ru.yahw.elbekd.financetracker.ui.wallet.WalletViewModel
import ru.yahw.elbekd.financetracker.ui.wallet.operations.NewWalletViewModel
import ru.yahw.elbekd.financetracker.ui.wallet.operations.PeriodicOperationVM
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionViewModel
import javax.inject.Singleton

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutVM(aboutVM: AboutViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(WalletViewModel::class)
    abstract fun bindWalletVM(balanceVM: WalletViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainVM(balanceVM: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindTransactionVM(transactionVM: TransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(NewWalletViewModel::class)
    abstract fun bindNewWalletVM(newWalletViewModel: NewWalletViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(PeriodicOperationVM::class)
    abstract fun bindPeriodicOperationVM(periodicOperationVM: PeriodicOperationVM): ViewModel


    @Binds
    @Singleton
    abstract fun bindVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}