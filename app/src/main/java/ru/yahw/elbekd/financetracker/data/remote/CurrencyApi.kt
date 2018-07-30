package ru.yahw.elbekd.financetracker.data.remote

import io.reactivex.Single
import org.json.JSONObject

/**
 * Created by Elbek D. on 30.07.2018.
 */
interface CurrencyApi {
    fun convert(from: String, to: String): Single<JSONObject>
}