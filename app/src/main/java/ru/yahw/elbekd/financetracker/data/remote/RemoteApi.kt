package ru.yahw.elbekd.financetracker.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import org.json.JSONObject

/**
 * Created by Elbek D. on 30.07.2018.
 */
class RemoteApi : CurrencyApi {
    companion object {
        const val URL = "https://free.currencyconverterapi.com/api/v6/convert?q=%s_%s&compact=ultra"
    }

    override fun convert(from: String, to: String): Single<JSONObject> {
        return Rx2AndroidNetworking.get(String.format(URL, from, to))
                .build()
                .jsonObjectSingle
    }
}