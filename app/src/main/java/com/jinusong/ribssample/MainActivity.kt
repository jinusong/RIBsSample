package com.jinusong.ribssample

import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.jinusong.RIBsSample.root.RootBuilder
import com.jinusong.RIBsSample.root.RootView
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter


class MainActivity: RibActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView()
        }
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *>? {
        val rootBuilder = RootBuilder(
            object: RootBuilder.ParentComponent {}
        )

        return rootBuilder.build(parentViewGroup)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootView()
}
