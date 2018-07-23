package ru.yahw.elbekd.financetracker.ui.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Elbek D. on 22.07.2018.
 */
abstract class BaseActivity<V : BaseViewModel<*>> : AppCompatActivity() {
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}