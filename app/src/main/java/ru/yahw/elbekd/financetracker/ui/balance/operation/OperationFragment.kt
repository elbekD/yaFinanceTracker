package ru.yahw.elbekd.financetracker.ui.balance.operation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_operation.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.domain.model.Operation
import ru.yahw.elbekd.financetracker.ui.balance.SharedViewModel
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class OperationFragment : BaseFragment<SharedViewModel>(), OperationNavigation {
    companion object {
        @JvmStatic
        val TAG = OperationFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): Fragment {
            return OperationFragment()
        }
    }

    override fun getViewModel() =
            ViewModelProviders.of(activity)[SharedViewModel::class.java]
    override fun getLayoutId() = R.layout.fragment_operation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmOperationBtn.setOnClickListener { confirmOperation() }
    }

    private fun confirmOperation() {
        if (operationAmount.text?.toString()!!.isEmpty()) {
            showErrorMessage("Amount must bo positive value")
            return
        }
        val amount = operationAmount.text?.toString()!!.toInt()

        val type = when (operationType.checkedRadioButtonId) {
            R.id.income -> Operation.Type.INCOME
            R.id.expense -> Operation.Type.EXPENSE
            else -> throw IllegalArgumentException()
        }
        val currency = when (operationCurrency.checkedRadioButtonId) {
            R.id.rub -> Operation.Currency.RUBLE
            R.id.dollar -> Operation.Currency.DOLLAR
            else -> throw IllegalArgumentException()
        }
        vm.updateBalance(Operation(type, amount, currency))
        goBack()
    }

    override fun goBack() {
        hideKeyboard()
        activity.supportFragmentManager.popBackStack()
    }
}