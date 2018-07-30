package ru.yahw.elbekd.financetracker.data.db

import android.arch.lifecycle.LiveData
import ru.yahw.elbekd.financetracker.domain.model.Transaction

/**
 * Created by Elbek D. on 29.07.2018.
 */
interface TransactionDao {
    fun commitTransaction(t: Transaction)
    /**
     * Get all transactions of given wallet
     */
    fun walletTransactions(w: String): LiveData<List<Transaction>>
}