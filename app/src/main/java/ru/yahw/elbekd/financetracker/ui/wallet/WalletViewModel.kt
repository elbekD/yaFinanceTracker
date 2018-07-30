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

    private val stubWallets: List<Wallet> = generateListOfStubWallets()
    private val _wallets = MutableLiveData<List<Wallet>>().also { it.value = stubWallets }

    fun getWallets() = walletRepo.wallets()
    fun getWalletTransactions(walletName: String) = transactionRepo.walletTransactions(walletName)
    fun getWalletByName(name: String) = walletRepo.wallet(name)
    fun addWallet(w: Wallet) = walletRepo.wallet(w)
    fun deleteWallet(w: Wallet) = walletRepo.deleteWallet(w)
    fun commitTransaction(t: Transaction) = transactionRepo.commitTransaction(t)
    fun convertCurrency(from: String, to: String) = walletRepo.convertCurrency(from, to)
    fun getCategories() = categoryRepo.categories()

    private fun generateListOfStubWallets(): List<Wallet> {
        val cashWallet = Wallet("My cash wallet",
                "Cash",
                "RUB",
                "USD")

        val creditCardWallet = Wallet("My credit card wallet",
                "Credit card",
                "RUB",
                "USD")

        return listOf(cashWallet, creditCardWallet)
    }
}