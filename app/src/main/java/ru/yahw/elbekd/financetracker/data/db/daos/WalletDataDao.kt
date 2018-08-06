package ru.yahw.elbekd.financetracker.data.db.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Transaction
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData

/**
 * Created by Elbek D. on 29.07.2018.
 */
@Dao
interface WalletDataDao {

    @Query("SELECT * from walletData")
    fun getAllWalets(): LiveData<List<WalletData>>

    @Insert(onConflict = REPLACE)
    fun insertWallet(walletData: WalletData)

    @Query("Delete from walletData")
    fun deleteAllFromWallets()

    @Query("SELECT wallet_name FROM walletData")
    fun selectAllWalletsName(): LiveData<List<String>>
}