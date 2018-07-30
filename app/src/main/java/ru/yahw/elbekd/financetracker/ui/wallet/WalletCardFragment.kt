package ru.yahw.elbekd.financetracker.ui.wallet

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat.getColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.cardview_wallet.view.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.domain.model.Transaction
import ru.yahw.elbekd.financetracker.domain.model.Wallet
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment

/**
 * Created by Elbek D. on 28.07.2018.
 */
class WalletCardFragment : BaseFragment<WalletViewModel>(), Injectable {
    override fun getLayoutId() = R.layout.cardview_wallet

    companion object {
        @JvmStatic
        val TAG = WalletCardFragment::class.java.simpleName

        const val WALLET_NAME_EXTRA = "WALLET_NAME"

        @JvmStatic
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

    private fun bindWallet(v: View, wallet: Wallet) {
        vm.getWalletTransactions(wallet.name).observe(this, Observer {
            it?.let {
                val remainder = it.asSequence().sumByDouble { it.amount.toDouble() }
                with(v) {
                    account_title.text = wallet.name
                    tv_main_currency_value.text = String.format("%.2f", remainder)
                    tv_main_currency_name.text = wallet.mainCurrency
                    tv_secondary_currency_value.text = remainder.toString()
                    tv_secondary_currency_name.text = wallet.secondaryCurrency
                }
                setupPieChart(v, it)
                setupCurrency(v, wallet)
            }
        })
    }

    private fun setupCurrency(v: View, w: Wallet) {
        vm.convertCurrency(w.mainCurrency, w.secondaryCurrency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val c = it["${w.mainCurrency}_${w.secondaryCurrency}"].toString().toDouble()
                    with(v) {

                        with(findViewById<TextView>(R.id.tv_current_currency)) {
                            text = String.format(getString(R.string.template_currency), w.mainCurrency, c, w.secondaryCurrency)
                            setBackgroundColor(getColor(context, R.color.health))
                        }

                        with(findViewById<TextView>(R.id.tv_secondary_currency_value)) {
                            text = String.format("%.2f", text.toString().toDouble() * c)
                        }
                    }
                }, {
                    showErrorMessage(it.message ?: getString(R.string.unknown_error))
                })
    }

    private fun setupPieChart(v: View, transactions: List<Transaction>) {
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