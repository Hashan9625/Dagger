package com.dmhashanmd.dagger.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
class IntoSetModule {

    @IntoSet
    @Provides
    fun provideFirstString(): String = "First string"

    @IntoSet
    @Provides
    fun provideSecondString(): String = "Second string"
}