package ru.yahw.elbekd.financetracker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import ru.yahw.elbekd.financetracker.ui.about.AboutFragment
import ru.yahw.elbekd.financetracker.ui.main.MainFragment
import ru.yahw.elbekd.financetracker.ui.settings.SettingsFragment
import ru.yahw.elbekd.financetracker.utils.replace
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        supportFragmentManager.replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
    }

    private fun openSettings() {
        supportFragmentManager
                .replace(R.id.container,
                        SettingsFragment.newInstance(),
                        SettingsFragment.TAG)
    }

    private fun openAbout() {
        supportFragmentManager
                .replace(R.id.container,
                        AboutFragment.newInstance(),
                        AboutFragment.TAG)
    }

    private fun openOperation() {

    }

    private fun openBalance() {
        supportFragmentManager.replace(R.id.container,
                MainFragment.newInstance(),
                MainFragment.TAG)
    }

    private fun setupBottomNavigation() {
        val adapter = AHBottomNavigationAdapter(this, R.menu.bottombar_menu)
        adapter.setupWithBottomNavigation(bottomNavigation)
        bottomNavigation.currentItem = 0
        bottomNavigation.setOnTabSelectedListener { position, _ ->
            when (position) {
                0 -> openBalance()
                1 -> openSettings()
                2 -> openAbout()
            }
            true
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
