package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.domain.repository.CategoryRepo
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

class PeriodicOperationVM @Inject constructor(private val walletRepo: WalletRepo,
                                              private val transactionRepo: TransactionRepo,
                                              private val categoryRepo: CategoryRepo) : ViewModel() {

    private val transactionWallet = MutableLiveData<String>()

    val transactionCurrency: LiveData<WalletData> = Transformations.switchMap(transactionWallet) {
        walletRepo.wallet(it)
    }

    fun setTransactionWallet(walletName: String) {
        if (walletName == transactionWallet.value) return
        transactionWallet.value = walletName
    }

    fun getCurrenciesType() = walletRepo.getCurrenciesType()

    fun getWalletsNames() = walletRepo.getWalletsNames()

    fun getAvailableCategories() = categoryRepo.categories()
    fun getTimeTypes() = walletRepo.getTimeTypes()

    fun commitTransaction(t: TransactionData) = transactionRepo.commitTransaction(t)
}