package ru.yahw.elbekd.financetracker.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Elbek D. on 22.07.2018.
 */
fun FragmentManager.replace(container: Int, fr: Fragment, tag: String) {
    if (findFragmentByTag(tag) != null) return
    beginTransaction().replace(container, fr).commit()
}

fun FragmentManager.replaceWithBackStack(container: Int, fr: Fragment, tag: String) {
    if (findFragmentByTag(tag) != null) return
    beginTransaction().replace(container, fr).addToBackStack(tag).commit()
}