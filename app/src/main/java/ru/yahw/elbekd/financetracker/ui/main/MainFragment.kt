package ru.yahw.elbekd.financetracker.ui.main

import android.app.ActionBar
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletCardFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletPagerAdapter
import ru.yahw.elbekd.financetracker.ui.wallet.operations.NewWalletDialogFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.PeriodicOperationFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionDialogFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class MainFragment : BaseFragment<MainFragmentViewModel>(), Injectable {
    private var isFABOpen: Boolean = false

    companion object {
        val TAG: String = MainFragment::class.java.simpleName
        fun newInstance() = MainFragment()
    }

    private lateinit var vm: MainFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()

        vm.getAllWallets().observe(this, Observer {
            it?.let { list ->
                val fragmentList = mutableListOf<Fragment>()
                list.forEach { w ->
                    fragmentList.add(WalletCardFragment
                            .newInstance(Bundle().apply { putString(WalletCardFragment.WALLET_NAME_EXTRA, w.walletName) }))
                }
                cardview_wallet_pager.adapter = WalletPagerAdapter(childFragmentManager, fragmentList)
            }
        })

        setupFabButtons()
        setClickFabButtons()
    }

    override fun getLayoutId() = R.layout.fragment_main

    private fun setupFabButtons() = fab.setOnClickListener {
        if (!isFABOpen) {
            expandFAB()
        } else {
            closeFab()
        }
    }

    private fun setClickFabButtons() {
        fab_transaction.setOnClickListener { TransactionDialogFragment.newInstance().show(fragmentManager, null) }
        fab_add_wallet.setOnClickListener { NewWalletDialogFragment.newInstance().show(fragmentManager, null) }
        fab_pereodic_transaction.setOnClickListener { PeriodicOperationFragment.newInstance().show(fragmentManager, null) }
    }

    private fun expandFAB() {
        isFABOpen = true
        fab_transaction.animate().translationY((-resources.getDimension(R.dimen.animate_fab_gravity_Y_115)))
        fab_add_wallet.animate().translationY((-resources.getDimension(R.dimen.animate_fab_gravity_Y_165)))
        fab_pereodic_transaction.animate().translationY(-resources.getDimension(R.dimen.animate_fab_gravity_Y_215))
    }

    private fun closeFab() {
        isFABOpen = false

        fab_transaction.animate().translationY(100f)
        fab_add_wallet.animate().translationY(100f)
        fab_pereodic_transaction.animate().translationY(100f)

    }
}