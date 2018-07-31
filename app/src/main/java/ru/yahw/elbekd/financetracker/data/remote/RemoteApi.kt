package ru.yahw.elbekd.financetracker.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Elbek D. on 30.07.2018.
 */
class RemoteApi @Inject constructor(private val client: OkHttpClient) : CurrencyApi {
    companion object {
        const val URL = "https://free.currencyconverterapi.com/api/v6/convert?q=%s_%s&compact=ultra"
    }

    override fun convert(from: String, to: String): LiveData<Double> {
        val res = MutableLiveData<Double>().apply { value = 0.0 }

        val req = Request.Builder()
                .url(URL.format(from, to))
                .build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.let {
                    it.body()?.let {
                        val obj = JSONObject(it.string())
                        val rate = obj.getDouble("${from}_$to")
                        res.postValue(rate)
                    }
                }
            }
        })

        return res
    }
}