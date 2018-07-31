package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import ru.yahw.elbekd.financetracker.domain.repository.CategoryRepo
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class TransactionViewModel @Inject constructor(
        private val walletRepo: WalletRepo,
        private val transactionRepo: TransactionRepo,
        private val categoryRepo: CategoryRepo) : ViewModel() {

    private val transactionWallet = MutableLiveData<String>()

    val transactionCurrency: LiveData<Wallet> = Transformations.switchMap(transactionWallet) {
        walletRepo.wallet(it)
    }

    fun setTransactionWallet(walletName: String) {
        if (walletName == transactionWallet.value) return
        transactionWallet.value = walletName
    }

    fun getAvailableWallets() = walletRepo.wallets()

    fun getAvailableCategories() = categoryRepo.categories()

    fun commitTransaction(t: Transaction) = transactionRepo.commitTransaction(t)
}