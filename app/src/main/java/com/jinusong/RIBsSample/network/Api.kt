package com.jinusong.RIBsSample.network

import com.jinusong.RIBsSample.root.meal.Meal
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("meal/{date}")
    fun getMeal(@Path("date") date: String): Single<Meal>

}
