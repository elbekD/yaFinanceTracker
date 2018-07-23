package ru.yahw.elbekd.financetracker.ui.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.yahw.elbekd.financetracker.R

/**
 * Created by Elbek D. on 22.07.2018.
 */
class SettingsFragment : Fragment() {
    companion object {
        @JvmStatic
        val TAG = SettingsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}