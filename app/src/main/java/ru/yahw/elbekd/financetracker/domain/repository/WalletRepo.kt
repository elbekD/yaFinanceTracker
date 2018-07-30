package ru.yahw.elbekd.financetracker.domain.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import ru.yahw.elbekd.financetracker.data.remote.CurrencyApi
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class WalletRepo @Inject constructor(private val currencyApi: CurrencyApi/*private val walletDao: WalletDao*/) {
    fun wallet(w: Wallet) {
        Log.d("add wallet", w.toString())
    }//walletDao.wallet(w)

    fun wallet(name: String) = MutableLiveData<Wallet>().apply { value = stubWallets.find { it.name == name }!! }//walletDao.wallet(name)
    fun wallets() = MutableLiveData<List<Wallet>>().apply { value = stubWallets } //walletDao.wallets()
    fun deleteWallet(w: Wallet) {
        Log.d("delete wallet", w.toString())
    }//walletDao.deleteWallet(w)

    fun convertCurrency(from: String, to: String) = currencyApi.convert(from, to)

    private companion object {
        val stubWallets = listOf(
                Wallet("Cash", "Cash", "RUB", "USD"),
                Wallet("Credit", "Credit", "USD", "RUB"))
    }
}