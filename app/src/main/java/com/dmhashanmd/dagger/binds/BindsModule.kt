package com.dmhashanmd.dagger.binds

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class BindsModule {

    @Binds
    abstract fun bindInterface(bindsInterfaceImpl: BindsInterfaceImpl): BindsInterface
}