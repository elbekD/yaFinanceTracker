package ru.yahw.elbekd.financetracker.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.daos.TransactionDataDao
import ru.yahw.elbekd.financetracker.data.db.daos.WalletDataDao
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.domain.model.Wallet

//TODO remake start wallet screen
@Database(entities = arrayOf(WalletData::class, TransactionData::class), version = 1)
@TypeConverters(Convecters::class)
abstract class WalletDataBase : RoomDatabase() {


    abstract fun walletDataDao(): WalletDataDao
    abstract fun transactionDataDao(): TransactionDataDao

    companion object {
        private val DB_NAME = "wallet.db"

        val stubWallets = WalletData("Cash", "Cash", "RUB", "250")


        @Volatile
        private var INSTANCE: WalletDataBase? = null


        fun getInstance(context: Context): WalletDataBase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }


        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, WalletDataBase::class.java, DB_NAME)
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Completable.fromAction { getInstance(context).walletDataDao().insertWallet(stubWallets) }
                                        .subscribeOn(Schedulers.io())
                                        .subscribe()
                            }
                        })
                        .build()

    }


}