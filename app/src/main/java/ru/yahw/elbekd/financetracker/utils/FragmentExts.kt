package ru.yahw.elbekd.financetracker.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ru.yahw.elbekd.financetracker.R

/**
 * Created by Elbek D. on 22.07.2018.
 */
fun FragmentManager.replace(container: Int, fr: Fragment, tag: String, rootTag: String) {
    if (findFragmentByTag(tag) != null) return
    popBackStack(rootTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    beginTransaction()
            .setCustomAnimations(R.anim.right_in, R.anim.left_out)
            .replace(container, fr)
            .addToBackStack(null)
            .commit()
}

fun FragmentManager.addOnTop(container: Int, fr: Fragment, tag: String, rootTag: String) {
    if (findFragmentByTag(tag) != null) return
    beginTransaction()
            .setCustomAnimations(R.anim.right_in, R.anim.left_out)
            .replace(container, fr)
            .addToBackStack(null)
            .commit()
}