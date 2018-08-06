package ru.yahw.elbekd.financetracker.domain.model

/**
 * Created by Elbek D. on 28.07.2018.
 */
data class Wallet(val name: String,
                  val type: String,
                  val mainCurrency: String,
                  val sum: String
)