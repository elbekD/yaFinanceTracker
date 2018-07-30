package ru.yahw.elbekd.financetracker.ui.wallet.operations

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
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
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
        @JvmStatic
        val TAG = TransactionDialogFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = TransactionDialogFragment()
    }

    private lateinit var vm: TransactionViewModel
    private lateinit var dialog: MaterialDialog
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

        val dialogBuilder = MaterialDialog.Builder(activity!!)
                .customView(R.layout.dialog_transaction, true)
                .title(R.string.add_transaction)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .onPositive { _, _ -> vm.commitTransaction(gatherTransaction()) }

        dialog = dialogBuilder.build()
        dialog.getActionButton(DialogAction.POSITIVE).isEnabled = false
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(dialog.customView!!)
        setupAdapters(dialog.customView!!)
        setupCurrency(dialog.customView!!)
        addAmountTextListener(dialog.customView!!)
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

                        override fun onItemSelected(root: AdapterView<*>, view: View, position: Int, id: Long) = vm.setTransactionWallet(selectedItem.toString())
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
        v.findViewById<EditText>(R.id.input_amount).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(e: Editable?) {
                e?.let {
                    val isEnabled = it.toString().matches("""^\d+(\.\d*)?$""".toRegex())
                    dialog.getActionButton(DialogAction.POSITIVE).isEnabled = isEnabled
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setupCurrency(v: View) {
        vm.transactionCurrency.observe(this, Observer {
            it?.let { v.findViewById<TextView>(R.id.tv_transaction_currency).text = it.mainCurrency }
        })
    }

    private fun gatherTransaction(): Transaction {
        val v = dialog.customView!!
        val formatter = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())
        val date = formatter.parse(v.findViewById<TextView>(R.id.tv_transaction_date).text.toString()).time
        val currency = v.findViewById<TextView>(R.id.tv_transaction_currency).text.toString()
        val type = v.findViewById<Spinner>(R.id.spinner_category).selectedItem.toString()
        val wallet = v.findViewById<Spinner>(R.id.spinner_wallets).selectedItem.toString()
        val isNegative = v.findViewById<RadioGroup>(R.id.operation_type).checkedRadioButtonId == R.id.income
        val amount = v.findViewById<EditText>(R.id.input_amount).text!!.toString()
        return Transaction(wallet, date, BigDecimal(amount).makeNegative(isNegative), currency, type)
    }
}