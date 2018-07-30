package ru.yahw.elbekd.financetracker.ui.about

import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class AboutFragment : BaseFragment<AboutViewModel>(), Injectable {
    companion object {
        @JvmStatic
        val TAG = AboutFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun getLayoutId() = R.layout.fragment_about
}