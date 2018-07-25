package ru.yahw.elbekd.financetracker.ui.main

import ru.yahw.elbekd.financetracker.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
class MainViewModel @Inject constructor() : BaseViewModel<MainNavigator>() {
    fun onSettingsClick() = navigator.openSettings()
    fun onAboutClick() = navigator.openAbout()
}