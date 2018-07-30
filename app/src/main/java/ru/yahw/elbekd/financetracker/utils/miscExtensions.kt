package ru.yahw.elbekd.financetracker.utils

import java.math.BigDecimal

/**
 * Created by Elbek D. on 29.07.2018.
 */
fun BigDecimal.makeNegative(isNegative: Boolean): BigDecimal = if (isNegative) negate() else this
