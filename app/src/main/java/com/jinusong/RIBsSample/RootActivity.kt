package com.jinusong.ribssample

import android.view.ViewGroup
import com.jinusong.RIBsSample.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter


class RootActivity: RibActivity() {

    override fun createRouter(parent: ViewGroup): ViewRouter<*, *, *> {
        val rootBuilder = RootBuilder(
            object: RootBuilder.ParentComponent {}
        )

        return rootBuilder.build(parent)
    }

}
