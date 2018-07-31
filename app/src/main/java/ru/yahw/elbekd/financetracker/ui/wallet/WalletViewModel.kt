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
        private val transactionRepo: TransactionRepo,
        private val categoryRepo: CategoryRepo) : ViewModel() {

    private val _walletTransactions = MutableLiveData<List<Transaction>>()

    fun getWallets() = walletRepo.wallets()

    fun getWalletTransactions(walletName: String) = transactionRepo.walletTransactions(walletName)

    fun getWalletByName(name: String) = walletRepo.wallet(name)

    fun addWallet(w: Wallet) = walletRepo.addWallet(w)

    fun deleteWallet(w: Wallet) = walletRepo.deleteWallet(w)

    fun commitTransaction(t: Transaction) = transactionRepo.commitTransaction(t)

    fun convertCurrency(from: String, to: String) = walletRepo.getExchangeRate(from, to)

    fun getCategories() = categoryRepo.categories()
}