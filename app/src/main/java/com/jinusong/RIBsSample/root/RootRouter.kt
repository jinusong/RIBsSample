package com.jinusong.RIBsSample.root

import com.jinusong.RIBsSample.root.meal.MealBuilder
import com.jinusong.RIBsSample.root.meal.MealRouter
import com.uber.rib.core.ViewRouter


class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    val mealBuilder: MealBuilder
): ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private lateinit var mealRouter: MealRouter

    fun attachMeal() {
        mealRouter = mealBuilder.build(view)
        attachChild(mealRouter)
        view.addView(mealRouter.view)
    }

}
