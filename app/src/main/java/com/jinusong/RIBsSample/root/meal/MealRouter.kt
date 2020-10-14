package com.jinusong.RIBsSample.root.meal

import com.uber.rib.core.ViewRouter


class MealRouter(
    view: MealView,
    interactor: MealInteractor,
    component: MealBuilder.Component
): ViewRouter<MealView, MealInteractor, MealBuilder.Component>(view, interactor, component)
