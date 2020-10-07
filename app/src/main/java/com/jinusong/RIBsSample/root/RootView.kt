package com.jinusong.RIBsSample.root

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview


@Composable
fun RootView() {
    Text(text = "Hello")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootView()
}

class RootPresenter: RootInteractor.RootPresenter {

}
