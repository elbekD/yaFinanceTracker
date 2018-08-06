package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseDialog

class NewWalletDialogFragment : BaseDialog<NewWalletViewModel>(), Injectable {
    companion object {
        private val nameWalletRegex = """^\w+$""".toRegex()
        fun newInstance() = NewWalletDialogFragment()
        val TAG = NewWalletDialogFragment::class.java.simpleName
    }

    private lateinit var vm: NewWalletViewModel
    private lateinit var newWalletDialogFragment: AlertDialog
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        vm = getViewModel()

        val inflater = activity!!.layoutInflater
        val dialogBuilder = AlertDialog.Builder(activity)
        val view = inflater.inflate(R.layout.dialog_create_wallet, null)

        dialogBuilder.setView(view)
                .setTitle(R.string.add_wallet)
                .setPositiveButton(R.string.add_wallet) { _, _ ->
                    vm.commitWallet(gatherWalletData())
                }
                .setNegativeButton(R.string.all_cancel) { _, _ -> }


        newWalletDialogFragment = dialogBuilder.create().apply {
            setOnShowListener { addNameTextListener(view) }
        }

        dialogView = view

        setupAdapters(view)

        return newWalletDialogFragment
    }

    private fun addNameTextListener(v: View) {
        with(v.findViewById<EditText>(R.id.input_wallet_name)) {

            val confirmButton = newWalletDialogFragment.getButton(AlertDialog.BUTTON_POSITIVE)
            confirmButton.isEnabled = text?.toString()?.matches(nameWalletRegex) ?: false

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    e?.let { confirmButton.isEnabled = it.toString().matches(nameWalletRegex) }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }

    private fun setupAdapters(v: View) {
        vm.getAviabileWallets().observe(this, Observer {
            it?.let {
                val wallets = it
                val walletAdapter = ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, wallets)
                with(v.findViewById<Spinner>(R.id.spinner_currency_type)) {
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }

                        override fun onItemSelected(root: AdapterView<*>, view: View?, position: Int, id: Long) {

                        }
                    }
                    adapter = walletAdapter
                }
            }
        })
    }

    private fun gatherWalletData(): WalletData {
        val walletType: String
        val walletName = dialogView.findViewById<EditText>(R.id.input_wallet_name).text!!.toString()
        val radioButtonCheck = dialogView.findViewById<RadioGroup>(R.id.operation_type).checkedRadioButtonId == R.id.credit
        val valuteType = dialogView.findViewById<Spinner>(R.id.spinner_currency_type).selectedItem.toString()
        if (radioButtonCheck) {
            walletType = dialogView.findViewById<RadioButton>(R.id.credit).text.toString()
        } else {
            walletType = dialogView.findViewById<RadioButton>(R.id.wallet).text.toString()
        }
        return WalletData(walletName, walletType, valuteType, "")
    }
}