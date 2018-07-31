package ru.yahw.elbekd.financetracker.ui.main

import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
class MainFragmentViewModel @Inject constructor(
        private val walletRepo: WalletRepo,
        private val transactionRepo: TransactionRepo
) : ViewModel() {

    fun getWallets() = walletRepo.wallets()

    fun getTransactions(walletName: String) = transactionRepo.walletTransactions(walletName)
}