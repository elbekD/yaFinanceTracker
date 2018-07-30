package ru.yahw.elbekd.financetracker.ui.wallet

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Elbek D. on 28.07.2018.
 */
class WalletPagerAdapter(fm: FragmentManager, private val list: List<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int) = list[position]
    override fun getCount() = list.size
}