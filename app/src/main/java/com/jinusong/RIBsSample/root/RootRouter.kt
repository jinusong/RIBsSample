package com.jinusong.RIBsSample.root

import android.view.View
import com.uber.rib.core.ViewRouter


class RootRouter(
    view: View,
    interactor: RootInteractor,
    component: RootBuilder.Component
): ViewRouter<View, RootInteractor, RootBuilder.Component>(view, interactor, component)
