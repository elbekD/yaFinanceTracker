package ru.yahw.elbekd.financetracker.domain.model

/**
 * Created by Elbek D. on 22.07.2018.
 */
data class Operation(val type: Type, val amount: Int, val currency: Currency) {
    enum class Type {
        INCOME, EXPENSE
    }

    enum class Currency {
        DOLLAR, RUBLE
    }
}