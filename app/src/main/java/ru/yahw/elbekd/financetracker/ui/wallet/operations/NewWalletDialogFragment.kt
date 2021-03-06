package ru.yahw.elbekd.financetracker.ui.wallet.operations

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseDialog
import ru.yahw.elbekd.financetracker.ui.wallet.WalletViewModel

/**
 * Created by Elbek D. on 29.07.2018.
 */
class NewWalletDialogFragment : BaseDialog<WalletViewModel>(), Injectable {
    private lateinit var vm: WalletViewModel
    private lateinit var dialog: AlertDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        vm = getViewModel()
        return dialog
    }
}