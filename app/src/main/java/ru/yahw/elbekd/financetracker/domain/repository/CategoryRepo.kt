package ru.yahw.elbekd.financetracker.domain.repository

import android.arch.lifecycle.MutableLiveData
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.domain.model.Category
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class CategoryRepo @Inject constructor(/*private val typeDao: CategoryDao*/) {
    private companion object {
        val types = listOf(
                Category("Food", R.color.food, R.drawable.ic_category_food),
                Category("Health", R.color.health, R.drawable.ic_category_health),
                Category("Home", R.color.home, R.drawable.ic_category_home),
                Category("Income", R.color.salary, R.drawable.ic_category_salary))
    }

    fun categories() = MutableLiveData<List<Category>>().apply { value = types }//typeDao.types()
}