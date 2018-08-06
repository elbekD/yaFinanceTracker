package ru.yahw.elbekd.financetracker.data.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.math.BigDecimal


@Entity(tableName = "transactionData")
data class TransactionData(@ColumnInfo(name = "wallet_name") var walletName: String,
                              @ColumnInfo(name = "date") var date: Long,
                              @ColumnInfo(name = "amount") var amount: BigDecimal,
                              @ColumnInfo(name = "currency") var currency: String,
                              @ColumnInfo(name = "type") var type: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @Ignore
    constructor() : this("", 0, BigDecimal.ZERO, "", "")
}