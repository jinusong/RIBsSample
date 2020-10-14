package com.jinusong.RIBsSample.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jinusong.RIBsSample.root.meal.MealBuilder
import com.jinusong.ribssample.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope


class RootBuilder(
    dependency: RootBuilder.ParentComponent
): ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {


    interface ParentComponent

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }

    fun build(parentViewGroup: ViewGroup): RootRouter {
        val view = createView(parentViewGroup)
        val interactor = RootInteractor()
        val component = DaggerRootBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.rootRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
        return inflater.inflate(R.layout.root_rib, parentViewGroup, false) as RootView
    }

    @dagger.Module
    abstract class Module {

        @RootScope
        @Binds
        abstract fun presenter(rootView: RootView): RootInteractor.RootPresenter

        companion object {
            @RootScope
            @Provides
            fun router(
                view: RootView, interactor: RootInteractor, component: Component,
            ): RootRouter {
                return RootRouter(view, interactor, component, MealBuilder(component))
            }
        }

    }

    @dagger.Module
    class NetworkModule {

        @RootScope
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        @RootScope
        @Provides
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @RootScope
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder().create()
        }

        @RootScope
        @Provides
        fun provideRetrofit(gson : Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
        }

    }

    @RootScope
    @dagger.Component(
        dependencies = [ParentComponent::class],
        modules = [Module::class, NetworkModule::class]
    )
    interface Component:
        InteractorBaseComponent<RootInteractor>, BuilderComponent, MealBuilder.ParentComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder
            @BindsInstance
            fun view(view: RootView): Builder

            fun parentComponent(component: RootBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RootScope

}
