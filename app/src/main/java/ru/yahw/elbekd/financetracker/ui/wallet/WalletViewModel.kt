package ru.yahw.elbekd.financetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import ru.yahw.elbekd.financetracker.domain.repository.CategoryRepo
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
// TODO retrieve from repo and update
class WalletViewModel @Inject constructor(
        private val walletRepo: WalletRepo,
        private val transactionRepo: TransactionRepo
) : ViewModel() {

    fun getWalletTransactions(walletName: String) = transactionRepo.getAllTransactionsByName(walletName)
    fun getAllTransactionData() = transactionRepo.getAllTransactionData()
    fun getWalletByName(name: String) = walletRepo.wallet(name)

}