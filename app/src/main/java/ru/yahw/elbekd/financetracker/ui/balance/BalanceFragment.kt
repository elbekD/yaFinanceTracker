package ru.yahw.elbekd.financetracker.ui.balance

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_balance.*
import ru.yahw.elbekd.financetracker.Injectable
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.ui.balance.operation.OperationFragment
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.utils.addOnTop
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
class BalanceFragment : BaseFragment<SharedViewModel>(), BalanceNavigator, Injectable {
    companion object {
        @JvmStatic
        val TAG: String = BalanceFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): Fragment {
            return BalanceFragment()
        }
    }

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var vm: SharedViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel(vmFactory)
        vm.getBalance().observe(activity!!, Observer {
            Log.d(TAG, it?.toString())
            balance1?.text = it?.ruble.toString()
            balance2?.text = it?.dollar.toString()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { openOperation() }
    }

    override fun getLayoutId() = R.layout.fragment_balance

    override fun openOperation() {
        fragmentManager
                ?.addOnTop(R.id.container,
                        OperationFragment.newInstance(),
                        OperationFragment.TAG,
                        TAG)
    }
}