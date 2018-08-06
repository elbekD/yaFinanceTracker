package ru.yahw.elbekd.financetracker.data.db

import android.arch.persistence.room.TypeConverter
import java.math.BigDecimal

class Convecters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): Long? {
        return value.longValueExact()
    }

    @TypeConverter
    fun fromLongToBigDecimal(value: Long): BigDecimal {
        return value.toBigDecimal()
    }
}