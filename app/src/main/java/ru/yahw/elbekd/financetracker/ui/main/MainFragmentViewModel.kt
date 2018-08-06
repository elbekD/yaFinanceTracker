package ru.yahw.elbekd.financetracker.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.data.db.WalletDataBase
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
class MainFragmentViewModel @Inject constructor(
        private val walletRepo: WalletRepo,
        private val transactionRepo: TransactionRepo,
        private val application: Application
) : ViewModel() {

    companion object {

    }
    private val allWallets: LiveData<List<WalletData>> = walletRepo.getAllWallets()

    fun getAllWallets(): LiveData<List<WalletData>> = allWallets

    fun insertWallet(walletData: WalletData) = walletRepo.insertWallet(walletData)

}