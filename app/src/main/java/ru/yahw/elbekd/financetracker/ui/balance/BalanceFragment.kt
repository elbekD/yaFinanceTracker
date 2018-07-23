package ru.yahw.elbekd.financetracker.ui.balance

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_balance.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.ui.balance.operation.OperationFragment
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.utils.addOnTop

/**
 * Created by Elbek D. on 22.07.2018.
 */
class BalanceFragment : BaseFragment<SharedViewModel>(), BalanceNavigator {
    companion object {
        @JvmStatic
        val TAG: String = BalanceFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): Fragment {
            return BalanceFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.getBalance().observe(activity, Observer {
            Log.d(TAG, it?.toString())
            balance1?.text = it?.ruble.toString()
            balance2?.text = it?.dollar.toString()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            fragmentManager
                    ?.addOnTop(R.id.container,
                            OperationFragment.newInstance(),
                            OperationFragment.TAG,
                            TAG)
        }
    }

    override fun getViewModel() =
            ViewModelProviders.of(activity)[SharedViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_balance
}