package ru.yahw.elbekd.financetracker.ui.balance

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.yahw.elbekd.financetracker.domain.model.Balance
import ru.yahw.elbekd.financetracker.domain.model.Operation
import ru.yahw.elbekd.financetracker.ui.base.BaseViewModel

/**
 * Created by Elbek D. on 22.07.2018.
 */
class SharedViewModel : BaseViewModel<BalanceNavigator>() {
    private val _balance = MutableLiveData<Balance>().also { it.value = Balance(1250, 650) }
    fun updateBalance(operation: Operation) {
        val b = _balance.value!!
        val am = when (operation.type) {
            Operation.Type.INCOME -> operation.amount
            Operation.Type.EXPENSE -> -operation.amount
        }
        val (rub, dol) = when (operation.currency) {
            Operation.Currency.RUBLE -> b.ruble + am to b.dollar
            Operation.Currency.DOLLAR -> b.ruble to b.dollar + am
        }
        _balance.value = Balance(rub, dol)
    }

    fun getBalance(): LiveData<Balance> = _balance
}