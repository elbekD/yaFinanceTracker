package ru.yahw.elbekd.financetracker.domain.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class TransactionRepo @Inject constructor(/*private val transactionDao: TransactionDao*/) {
    fun walletTransactions(walletName: String) = MutableLiveData<List<Transaction>>().apply { value = stubTransactions[walletName] } //transactionDao.walletTransactions(walletName)
    fun commitTransaction(t: Transaction) {
        Log.d("Commit transaction", t.toString())
    } //transactionDao.commitTransaction(t)

    private companion object {
        val stubTransactions = mapOf(
                "Cash" to listOf(Transaction("Cash", Calendar.getInstance().timeInMillis, BigDecimal(-253.00), "RUB", "House"),
                        Transaction("Cash", Calendar.getInstance().timeInMillis, BigDecimal(-125.00), "RUB", "Food"),
                        Transaction("Cash", Calendar.getInstance().timeInMillis, BigDecimal(652.00), "RUB", "Trip")),
                "Credit" to listOf(Transaction("Credit", Calendar.getInstance().timeInMillis, BigDecimal(-125.00), "RUB", "Food"),
                        Transaction("Credit", Calendar.getInstance().timeInMillis, BigDecimal(-326.50), "RUB", "House"),
                        Transaction("Credit", Calendar.getInstance().timeInMillis, BigDecimal(856.20), "RUB", "Trip"))
        )
    }
}