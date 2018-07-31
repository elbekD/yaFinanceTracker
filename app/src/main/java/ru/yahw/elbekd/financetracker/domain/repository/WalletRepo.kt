package ru.yahw.elbekd.financetracker.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.yahw.elbekd.financetracker.data.remote.CurrencyApi
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class WalletRepo @Inject constructor(private val currencyApi: CurrencyApi/*private val walletDao: WalletDao*/) {
    fun addWallet(w: Wallet) {
        //walletDao.addWallet(w)
    }

    fun wallet(name: String): LiveData<Wallet> = MutableLiveData<Wallet>().apply { value = stubWallets.find { it.name == name }!! }//walletDao.addWallet(name)

    fun wallets(): LiveData<List<Wallet>> = MutableLiveData<List<Wallet>>().apply { value = stubWallets } //walletDao.wallets()

    fun deleteWallet(w: Wallet) {
        //walletDao.deleteWallet(w)
    }

    fun getExchangeRate(from: String, to: String) = currencyApi.convert(from, to)

    private companion object {
        val stubWallets = listOf(
                Wallet("Cash", "Cash", "RUB", "USD"),
                Wallet("Credit", "Credit", "USD", "RUB"))
    }
}