package ru.yahw.elbekd.financetracker.ui.wallet

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.cardview_wallet.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.utils.formatDecimalNumber

/**
 * Created by Elbek D. on 28.07.2018.
 */
class WalletCardFragment : BaseFragment<WalletViewModel>(), Injectable {
    override fun getLayoutId() = R.layout.cardview_wallet

    companion object {
        val TAG = WalletCardFragment::class.java.simpleName
        const val WALLET_NAME_EXTRA = "WALLET_NAME"
        fun newInstance(args: Bundle) = WalletCardFragment().apply { arguments = args }
    }

    private lateinit var vm: WalletViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vm = getViewModel()
        val v = inflater.inflate(R.layout.cardview_wallet, container, false)

        vm.getWalletByName(arguments!!.getString(WALLET_NAME_EXTRA, "unknown"))
                .observe(this, Observer { wallet -> wallet?.let { bindWallet(v, it) } })

        return v
    }

    private fun bindWallet(v: View, wallet: WalletData) {
        account_title.text = wallet.walletName
        tv_main_currency_name.text = wallet.mauinCurrency
        //depricated
//        tv_secondary_currency_name.text = wallet.secondaryCurrency

        vm.getWalletTransactions(wallet.walletName).observe(this, Observer {
            it?.let {
                val remainder = it.asSequence().sumByDouble { it.amount.toDouble() }
                tv_main_currency_value.text = formatDecimalNumber(remainder)
                tv_secondary_currency_value.text = formatDecimalNumber(remainder)
                setupPieChart(v, it)
            }
        })

        //setupCurrency(wallet)
    }

    /* depricated curency change
    private fun setupCurrency(w: Wallet)   {
        vm.convertCurrency(w.mainCurrency).observe(this, Observer { rate ->
            rate?.let {
                tv_current_currency
                        .text = getString(R.string.template_currency)
                        .format(w.mainCurrency, formatDecimalNumber(rate))

                tv_secondary_currency_value
                        .text = formatDecimalNumber(tv_main_currency_value.text.toString().toDouble() * rate)
            }
        })
    }
*/
    private fun setupPieChart(v: View, transactions: List<TransactionData>) {
        val expenses = transactions.filter { it.amount.toFloat() < 0 }
        val overallAmount = expenses.asSequence().map { -it.amount.toFloat() }.sum()

        val list = mutableListOf<PieEntry>()
        expenses.forEach { list.add(PieEntry(overallAmount / -it.amount.toFloat(), it.type)) }

        val pieDataSet = PieDataSet(list, getString(R.string.all_expense))
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        pieDataSet.setDrawValues(false)
        with(v.findViewById<PieChart>(R.id.cardview_wallat_piechart)) {
            data = PieData(pieDataSet)
            invalidate()
        }
    }
}