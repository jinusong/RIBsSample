package com.jinusong.RIBsSample.root.meal

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.meal_rib.view.*


class MealView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attr, defStyleAttr), MealInteractor.MealPresenter {

    override fun setMealData(meal: Meal) {
        breakfast_tv.text = "아침: ${meal.breakfast}"
        luncht_tv.text = "점심: ${meal.lunch}"
        dinner_tv.text = "저녁: ${meal.dinner}"
    }

}
