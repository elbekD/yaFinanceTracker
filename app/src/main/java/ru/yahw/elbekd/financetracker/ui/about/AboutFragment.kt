package ru.yahw.elbekd.financetracker.ui.about

import android.arch.lifecycle.ViewModelProviders
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class AboutFragment : BaseFragment<AboutViewModel>(), AboutNavigator {
    companion object {
        @JvmStatic
        val TAG = AboutFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun getViewModel() =
            ViewModelProviders.of(this)[AboutViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_about
}