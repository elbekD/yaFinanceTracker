package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.View
import android.widget.*
import androidx.work.*
import ru.yahw.elbekd.financetracker.PeriodicSqlRequest
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseDialog
import ru.yahw.elbekd.financetracker.utils.makeNegative
import java.math.BigDecimal
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class PeriodicOperationFragment() : BaseDialog<PeriodicOperationVM>(), Injectable {
    companion object {
        private val amountRegex = """^\d+(\.\d*)?$""".toRegex()
        val TAG = TransactionDialogFragment::class.java.simpleName
        fun newInstance() = PeriodicOperationFragment()
    }

    private lateinit var vm: PeriodicOperationVM
    private lateinit var transactionDialog: AlertDialog
    private lateinit var dialogView: View
    private var wm: WorkManager = WorkManager.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        vm = getViewModel()
        val inflater = activity!!.layoutInflater
        val dialogBuilder = AlertDialog.Builder(activity)
        val view = inflater.inflate(R.layout.dialog_periodik_operation, null)

        dialogBuilder.setView(view)
                .setTitle(R.string.transaction_periodic_add)
                .setPositiveButton(R.string.all_confirm) { _, _ ->
                    setupWorkManager()
                }
                .setNegativeButton(R.string.all_cancel) { _, _ -> }

        transactionDialog = dialogBuilder.create().apply {
            setOnShowListener { addAmountTextListener(view) }
        }
        dialogView = view
        setupViews(view)
        return transactionDialog
    }

    private fun setupViews(v: View) {
        setupAdapters(v)
    }

    private fun setupAdapters(v: View) {
        vm.getCurrenciesType().observe(this, Observer {
            it?.let {
                val wallets = it.map { it }
                val walletAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, wallets)
                with(v.findViewById<Spinner>(R.id.spinner_category)) {
                    adapter = walletAdapter
                }
            }
        })

        vm.getAvailableCategories().observe(this, Observer {
            it?.let {
                val typeAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, it.map { it.name })
                v.findViewById<Spinner>(R.id.spinner_curency).adapter = typeAdapter
            }
        })

        vm.getWalletsNames().observe(this, Observer {
            it?.let {
                val walletsAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, it.map { it })
                v.findViewById<Spinner>(R.id.spinner_wallets).adapter = walletsAdapter
            }
        })

        vm.getTimeTypes().observe(this, Observer {
            it?.let {
                val timeAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, it.map { it })
                v.findViewById<Spinner>(R.id.spinner_perodic_time).adapter = timeAdapter
            }
        })
    }

    private fun addAmountTextListener(v: View) {
        with(v.findViewById<EditText>(R.id.input_amount)) {

            val confirmButton = transactionDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            confirmButton.isEnabled = text?.toString()?.matches(amountRegex) ?: false

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    e?.let { confirmButton.isEnabled = it.toString().matches(amountRegex) }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }

    private fun setupCurrency(v: View) {
        vm.transactionCurrency.observe(this, Observer {
            it?.let { v.findViewById<TextView>(R.id.tv_transaction_currency).text = it.mauinCurrency }
        })
    }

    private fun gatherTransaction(): Data {
        val type = dialogView.findViewById<Spinner>(R.id.spinner_category).selectedItem.toString()
        val walletCurrency = dialogView.findViewById<Spinner>(R.id.spinner_curency).selectedItem.toString()
        val isNegative = dialogView.findViewById<RadioGroup>(R.id.operation_type).checkedRadioButtonId == R.id.income
        val amount = dialogView.findViewById<EditText>(R.id.input_amount).text!!.toString()
        val wallet = dialogView.findViewById<Spinner>(R.id.spinner_wallets).selectedItem.toString()
        val chosenDate = dialogView.findViewById<Spinner>(R.id.spinner_perodic_time).selectedItem.toString()


        return Data.Builder()
                .putString("walletName", wallet)
                .putLong("date", Calendar.getInstance().timeInMillis)
                .putLong("amount", BigDecimal(amount).makeNegative(!isNegative).longValueExact())
                .putString("type", walletCurrency)
                .putString("walletCurrency", type)
                .putString("chosenDate", chosenDate)
                .build()
    }

    private fun setupWorkManager() {
        val sqlWorkBuilder = PeriodicWorkRequest.Builder(PeriodicSqlRequest::class.java, 15, TimeUnit.MINUTES)
                .setInputData(gatherTransaction())
                .build()

        wm.enqueue(sqlWorkBuilder)
    }

}