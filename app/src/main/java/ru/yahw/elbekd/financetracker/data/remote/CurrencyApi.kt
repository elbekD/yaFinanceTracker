package ru.yahw.elbekd.financetracker.data.remote

import android.arch.lifecycle.LiveData

/**
 * Created by Elbek D. on 30.07.2018.
 */
interface CurrencyApi {
    fun convert(from: String, to: String): LiveData<Double>
}