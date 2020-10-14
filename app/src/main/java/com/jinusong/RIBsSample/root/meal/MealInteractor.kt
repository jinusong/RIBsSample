package com.jinusong.RIBsSample.root.meal

import android.util.Log
import com.jinusong.RIBsSample.network.Api
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@RibInteractor
class MealInteractor: Interactor<MealInteractor.MealPresenter, MealRouter>() {

    @Inject
    lateinit var presenter: MealPresenter

    @Inject
    lateinit var api: Api

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        Log.e("asd", "asd")

        getMealData(
            SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Calendar.getInstance().time)
        )

    }

    private fun getMealData(date: String) {
        api.getMeal(date)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
            presenter.setMealData(response)
        }, {

        })
    }

    interface MealPresenter {
        fun setMealData(meal: Meal)
    }

}
