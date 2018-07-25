package ru.yahw.elbekd.financetracker.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Elbek D. on 22.07.2018.
 */
abstract class BaseFragment<V : BaseViewModel<*>> : Fragment() {
    abstract fun getLayoutId(): Int

    protected inline fun <reified T : V> getViewModel(factory: ViewModelProvider.Factory): T = ViewModelProviders.of(this, factory)[T::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun showErrorMessage(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    fun showMessage(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    fun hideKeyboard() {
        (activity as? BaseActivity<*>)?.hideKeyboard()
    }
}