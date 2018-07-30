package ru.yahw.elbekd.financetracker.domain.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

/**
 * Created by Elbek D. on 29.07.2018.
 */
data class Category(val name: String,
                    @ColorRes val color: Int,
                    @DrawableRes val icon: Int)