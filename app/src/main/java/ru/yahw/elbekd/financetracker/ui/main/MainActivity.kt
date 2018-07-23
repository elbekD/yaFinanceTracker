package ru.yahw.elbekd.financetracker.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.ui.about.AboutFragment
import ru.yahw.elbekd.financetracker.ui.balance.BalanceFragment
import ru.yahw.elbekd.financetracker.ui.base.BaseActivity
import ru.yahw.elbekd.financetracker.ui.settings.SettingsFragment
import ru.yahw.elbekd.financetracker.utils.replace

class MainActivity : BaseActivity<MainViewModel>(), NavigationView.OnNavigationItemSelectedListener, MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, BalanceFragment.newInstance(), BalanceFragment.TAG)
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                supportFragmentManager
                        .replace(R.id.container,
                                SettingsFragment.newInstance(),
                                SettingsFragment.TAG,
                                BalanceFragment.TAG)
            }
            R.id.about -> {
                supportFragmentManager
                        .replace(R.id.container,
                                AboutFragment.newInstance(),
                                AboutFragment.TAG,
                                BalanceFragment.TAG)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
