package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.view.View
import android.widget.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import ru.yahw.elbekd.financetracker.ui.base.BaseDialog
import ru.yahw.elbekd.financetracker.utils.makeNegative
import java.math.BigDecimal
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Elbek D. on 28.07.2018.
 */
class TransactionDialogFragment : BaseDialog<TransactionViewModel>(), Injectable {
    companion object {
        private val amountRegex = """^\d+(\.\d*)?$""".toRegex()
        val TAG = TransactionDialogFragment::class.java.simpleName
        fun newInstance() = TransactionDialogFragment()
    }

    private lateinit var vm: TransactionViewModel
    private lateinit var transactionDialog: AlertDialog
    private lateinit var dialogView: View

    private val calendar = MutableLiveData<Calendar>().apply { value = Calendar.getInstance() }

    private val datePickerDialog: DatePickerDialog by lazy {
        val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.value = calendar.value!!.apply { set(year, month, dayOfMonth) }
        }
        DatePickerDialog(activity, listener,
                calendar.value!!.get(Calendar.YEAR),
                calendar.value!!.get(Calendar.MONTH),
                calendar.value!!.get(Calendar.DAY_OF_MONTH))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        vm = getViewModel()
        val inflater = activity!!.layoutInflater
        val dialogBuilder = AlertDialog.Builder(activity)
        val view = inflater.inflate(R.layout.dialog_transaction, null)

        dialogBuilder.setView(view)
                .setTitle(R.string.transaction_add)
                .setPositiveButton(R.string.all_confirm) { _, _ ->
                    vm.commitTransaction(gatherTransaction())
                }
                .setNegativeButton(R.string.all_cancel) { _, _ -> }

        transactionDialog = dialogBuilder.create().apply {
            setOnShowListener { addAmountTextListener(view) }
        }
        dialogView = view
        setupViews(view)
        return transactionDialog
    }

    private fun setupDateTextView(v: TextView) {
        calendar.observe(this, Observer {
            it?.let {
                v.text = DateUtils.formatDateTime(activity, it.timeInMillis,
                        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE)
            }
        })
    }

    private fun setupViews(v: View) {
        with(v.findViewById<TextView>(R.id.tv_transaction_date)) {
            setupDateTextView(this)
            setOnClickListener { datePickerDialog.show() }
        }
        setupAdapters(v)
        setupCurrency(v)
    }

    private fun setupAdapters(v: View) {
        vm.getAvailableWallets().observe(this, Observer {
            it?.let {
                val wallets = it.map { it.name }
                val walletAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, wallets)
                with(v.findViewById<Spinner>(R.id.spinner_wallets)) {
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }

                        override fun onItemSelected(root: AdapterView<*>, view: View?, position: Int, id: Long) = vm.setTransactionWallet(selectedItem.toString())
                    }
                    adapter = walletAdapter
                }
            }
        })

        vm.getAvailableCategories().observe(this, Observer {
            it?.let {
                val typeAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, it.map { it.name })
                v.findViewById<Spinner>(R.id.spinner_category).adapter = typeAdapter
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
            it?.let { v.findViewById<TextView>(R.id.tv_transaction_currency).text = it.mainCurrency }
        })
    }

    private fun gatherTransaction(): Transaction {
        val formatter = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())
        val date = formatter.parse(dialogView.findViewById<TextView>(R.id.tv_transaction_date).text.toString()).time
        val currency = dialogView.findViewById<TextView>(R.id.tv_transaction_currency).text.toString()
        val type = dialogView.findViewById<Spinner>(R.id.spinner_category).selectedItem.toString()
        val wallet = dialogView.findViewById<Spinner>(R.id.spinner_wallets).selectedItem.toString()
        val isNegative = dialogView.findViewById<RadioGroup>(R.id.operation_type).checkedRadioButtonId == R.id.income
        val amount = dialogView.findViewById<EditText>(R.id.input_amount).text!!.toString()
        return Transaction(wallet, date, BigDecimal(amount).makeNegative(isNegative), currency, type)
    }
}