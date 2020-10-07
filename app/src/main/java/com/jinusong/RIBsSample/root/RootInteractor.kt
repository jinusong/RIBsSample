package com.jinusong.RIBsSample.root

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject


@RibInteractor
class RootInteractor: Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        // Add attachment logic here (RxSubscriptions, etc.).
    }

    interface RootPresenter

}