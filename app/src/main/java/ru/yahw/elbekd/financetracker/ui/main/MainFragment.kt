package ru.yahw.elbekd.financetracker.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletCardFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletPagerAdapter
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionDialogFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class MainFragment : BaseFragment<MainFragmentViewModel>(), Injectable {
    companion object {
        @JvmStatic
        val TAG: String = MainFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

    private lateinit var vm: MainFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()
        vm.getWallets().observe(this.viewLifecycleOwner, Observer {
            it?.let { list ->
                val fragmentList = mutableListOf<Fragment>()
                list.forEach { w ->
                    fragmentList.add(WalletCardFragment
                            .newInstance(Bundle().apply { putString(WalletCardFragment.WALLET_NAME_EXTRA, w.name) }))
                }
                cardview_wallet_pager.adapter = WalletPagerAdapter(childFragmentManager, fragmentList)
            }
        })
        setupFabButton()
    }

    override fun getLayoutId() = R.layout.fragment_main

    private fun setupFabButton() = fab.setOnClickListener {
        TransactionDialogFragment.newInstance().show(fragmentManager, null)
    }
}