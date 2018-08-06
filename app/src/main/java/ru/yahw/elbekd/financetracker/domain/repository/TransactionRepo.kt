package ru.yahw.elbekd.financetracker.domain.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.WalletDataBase
import ru.yahw.elbekd.financetracker.data.db.daos.TransactionDataDao
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class TransactionRepo @Inject constructor(private val application: Application) {

    private val walletDataBase: WalletDataBase = WalletDataBase.getInstance(application)

    private val transactionDao: TransactionDataDao = walletDataBase.transactionDataDao()

    fun getAllTransactionData(): LiveData<List<TransactionData>> = transactionDao.selectAllTransactions()
    fun getAllTransactionsByName(name: String): LiveData<List<TransactionData>> = transactionDao.selectTransactionByWalletName(name)

    fun commitTransaction(t: TransactionData) {
        Completable.fromAction { transactionDao.insertTransaction(t) }
                .subscribeOn(Schedulers.io())
                .subscribe()
        Log.d("Commit transaction", t.toString())
    }

}