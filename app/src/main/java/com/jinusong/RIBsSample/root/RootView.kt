package com.jinusong.RIBsSample.root

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RootView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attr, defStyleAttr), RootInteractor.RootPresenter
