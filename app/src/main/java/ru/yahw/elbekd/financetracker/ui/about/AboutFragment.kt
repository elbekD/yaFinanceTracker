package ru.yahw.elbekd.financetracker.ui.about

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import ru.yahw.elbekd.financetracker.Injectable
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
class AboutFragment : BaseFragment<AboutViewModel>(), AboutNavigator, Injectable {
    companion object {
        @JvmStatic
        val TAG = AboutFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var vm: AboutViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel(vmFactory)
    }

    override fun getLayoutId() = R.layout.fragment_about

    override fun onNavBackClick() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }
}