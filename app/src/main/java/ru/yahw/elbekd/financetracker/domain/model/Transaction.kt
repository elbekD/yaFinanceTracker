package ru.yahw.elbekd.financetracker.domain.model

import java.math.BigDecimal

/**
 * Created by Elbek D. on 28.07.2018.
 */
data class Transaction(val wallet: String,
                       val date: Long,
                       val amount: BigDecimal,
                       val currency: String,
                       val type: String)