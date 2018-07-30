package ru.yahw.elbekd.financetracker.data.db

import android.arch.lifecycle.LiveData
import ru.yahw.elbekd.financetracker.domain.model.Category

/**
 * Created by Elbek D. on 29.07.2018.
 */
interface CategoryDao {
    /**
     * Add new type
     */
    fun type(t: Category)

    fun deleteType(t: Category)

    /**
     * Get available types
     */
    fun types(): LiveData<List<Category>>
}