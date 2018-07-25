package ru.yahw.elbekd.financetracker.ui.about

import ru.yahw.elbekd.financetracker.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
class AboutViewModel @Inject constructor() : BaseViewModel<AboutNavigator>() {
    fun onNavBackClick() = navigator.onNavBackClick()
}