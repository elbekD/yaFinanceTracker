package ru.yahw.elbekd.financetracker.data.db.entities

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class WalletAllTransactions {
    @Embedded
    var walletData: WalletData = WalletData()

    @Relation(parentColumn = "wallet_name", entityColumn = "wallet_name", entity = TransactionData::class)
    var transactions: List<TransactionData> = listOf()
}