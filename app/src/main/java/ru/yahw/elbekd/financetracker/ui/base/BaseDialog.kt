package ru.yahw.elbekd.financetracker.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.DialogFragment
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
abstract class BaseDialog<V : ViewModel> : DialogFragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    protected inline fun <reified T : V> getViewModel(): T = ViewModelProviders.of(this, vmFactory)[T::class.java]
}