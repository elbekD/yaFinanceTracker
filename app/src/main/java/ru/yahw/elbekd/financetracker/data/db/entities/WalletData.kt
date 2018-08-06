package ru.yahw.elbekd.financetracker.data.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "walletData")
data class WalletData(
        @ColumnInfo(name = "wallet_name") var walletName: String,
        @ColumnInfo(name = "type") var walletType: String,
        @ColumnInfo(name = "main_currency") var mauinCurrency: String,
        @ColumnInfo(name = "sum") var sum: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @Ignore
    constructor() : this("", "", "", "")
}