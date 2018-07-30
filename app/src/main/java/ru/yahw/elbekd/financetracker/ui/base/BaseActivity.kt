package ru.yahw.elbekd.financetracker.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
abstract class BaseActivity<V : ViewModel> : AppCompatActivity() {
    protected inline fun <reified T : V> getViewModel(): T = ViewModelProviders.of(this, vmFactory)[T::class.java]

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}