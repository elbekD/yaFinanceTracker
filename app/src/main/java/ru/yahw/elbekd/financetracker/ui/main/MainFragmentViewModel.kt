package ru.yahw.elbekd.financetracker.ui.main

import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.ui.wallet.WalletViewModel
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
class MainFragmentViewModel @Inject constructor(private val walletVM: WalletViewModel) : ViewModel() {
    fun getWallets() = walletVM.getWallets()
}