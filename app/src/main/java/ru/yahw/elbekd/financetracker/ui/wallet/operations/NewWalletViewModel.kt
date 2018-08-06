package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

class NewWalletViewModel @Inject constructor(private val walletRepo: WalletRepo) : ViewModel() {

    fun commitWallet(w: WalletData) = walletRepo.insertWallet(w)

    fun getAviabileWallets() = walletRepo.getCurrenciesType()
}