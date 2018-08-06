package ru.yahw.elbekd.financetracker

import android.app.Application
import androidx.work.Data
import androidx.work.Worker
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import java.math.BigDecimal

class PeriodicSqlRequest : Worker() {
    val transactionRepo: TransactionRepo = TransactionRepo(Application())

    override fun doWork(): Result {

        val data = inputData
        val name = data.getString(WorkerKeys.WALLET_NAME_KEY)
        val date = data.getLong(WorkerKeys.DATE_KEY, 0)
        val amount = data.getLong(WorkerKeys.AMOUNT_KEY, 0).toBigDecimal()
        val currency = data.getString(WorkerKeys.WALLET_CURRENCY_KEY)
        val type = data.getString(WorkerKeys.TYPE_KEY)

        val transactionData = TransactionData(name!!, date, amount, currency!!, type!!)

        transactionRepo.commitTransaction(transactionData)

        return Worker.Result.SUCCESS
    }

}