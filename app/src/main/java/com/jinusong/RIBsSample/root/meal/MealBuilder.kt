package com.jinusong.RIBsSample.root.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jinusong.RIBsSample.network.Api
import com.jinusong.ribssample.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Scope


class MealBuilder(
    dependency: MealBuilder.ParentComponent
): ViewBuilder<MealView, MealRouter, MealBuilder.ParentComponent>(dependency) {

    interface ParentComponent {
        fun retrofitBuilder(): Retrofit.Builder
    }

    interface BuilderComponent {
        fun mealRouter(): MealRouter
    }

    fun build(parentViewGroup: ViewGroup): MealRouter {
        val view = createView(parentViewGroup)
        val interactor = MealInteractor()
        val component = DaggerMealBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.mealRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MealView {
        return inflater.inflate(R.layout.meal_rib, parentViewGroup, false) as MealView
    }

    @dagger.Module
    abstract class Module {

        @MealScope
        @Binds
        abstract fun presenter(mealView: MealView): MealInteractor.MealPresenter

        companion object {
            @MealScope
            @Provides
            fun router(
                view: MealView, interactor: MealInteractor, component: Component
            ): MealRouter {
                return MealRouter(view, interactor, component)
            }
        }

    }

    @dagger.Module
    class ApiModule {

        companion object {
            private val baseUrl = "https://dev-api.dsm-dms.com/"
        }

        @MealScope
        @Provides
        fun provideApi(retrofit: Retrofit.Builder): Api =
            retrofit.baseUrl(baseUrl)
                .build()
                .create(Api::class.java)

    }

    @MealScope
    @dagger.Component(
        dependencies = [ParentComponent::class],
        modules = [Module::class, ApiModule::class]
    )
    interface Component: InteractorBaseComponent<MealInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: MealInteractor): Builder
            @BindsInstance
            fun view(view: MealView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MealScope

}
