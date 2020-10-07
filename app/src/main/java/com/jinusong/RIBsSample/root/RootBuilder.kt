package com.jinusong.RIBsSample.root

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope


class RootBuilder(
    val dependency: RootBuilder.ParentComponent
): ViewBuilder<View, RootRouter, RootBuilder.ParentComponent>(dependency) {


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

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): View {
        return ComposeView(parentViewGroup.context).apply {
            setContent {
                RootView()
            }
        }
    }

    @dagger.Module
    class Module {

        @RootScope
        @Provides
        fun presenter(): RootInteractor.RootPresenter = RootPresenter()

        @RootScope
        @Provides
        fun router(
            view: View, interactor: RootInteractor, component: Component
        ): RootRouter {
            return RootRouter(view, interactor, component)
        }

    }

    @RootScope
    @dagger.Component(
        dependencies = [ParentComponent::class],
        modules = [Module::class]
    )
    interface Component: InteractorBaseComponent<RootInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder
            @BindsInstance
            fun view(view: View): Builder

            fun parentComponent(component: RootBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RootScope

}
