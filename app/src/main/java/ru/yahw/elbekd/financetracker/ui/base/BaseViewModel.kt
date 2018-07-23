package ru.yahw.elbekd.financetracker.ui.base

import android.arch.lifecycle.ViewModel
import java.lang.ref.WeakReference

/**
 * Created by Elbek D. on 22.07.2018.
 */
abstract class BaseViewModel<N> : ViewModel() {
    private lateinit var _navigator: WeakReference<N>
    var navigator: N
        get() = _navigator.get()!!
        set(value) {_navigator = WeakReference(value!!) }
}