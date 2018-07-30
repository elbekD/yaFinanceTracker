package ru.yahw.elbekd.financetracker.data.db

import android.arch.lifecycle.LiveData
import ru.yahw.elbekd.financetracker.domain.model.Wallet

/**
 * Created by Elbek D. on 29.07.2018.
 */
interface WalletDao {
    /**
     * Save wallet
     */
    fun wallet(w: Wallet)

    /**
     * Get wallet by name
     */
    fun wallet(name: String): LiveData<Wallet>

    /**
     * Get all wallets
     */
    fun wallets(): LiveData<List<Wallet>>

    fun deleteWallet(w: Wallet)
}