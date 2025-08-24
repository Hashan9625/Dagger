package com.dmhashanmd.dagger.contentProvider
import com.dmhashanmd.dagger.module.AppModule
import dagger.Component
import dagger.Subcomponent

@Component(modules = [AppModule::class])
interface ParentComponent {
    fun childComponentFactory(): ChildComponent.Factory
}

@Subcomponent
interface ChildComponent {
    fun inject(customContentProvider: CustomContentProvider)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChildComponent
    }
}
