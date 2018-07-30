package ru.yahw.elbekd.financetracker.utils

import android.arch.lifecycle.LiveData

/**
 * Created by Elbek D. on 29.07.2018.
 */
class AbsentLiveData<T : Any?> : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> = AbsentLiveData()
    }
}