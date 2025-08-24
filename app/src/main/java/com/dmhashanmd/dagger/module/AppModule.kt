package com.dmhashanmd.dagger.module

import com.dmhashanmd.dagger.model.Engine
import com.dmhashanmd.dagger.model.Transmission
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule
{
    @Provides
    fun provideEngine(): Engine{
      return Engine("Petrol")
    }

    @Named("app")
    @Provides
    fun provideTransmission(): Transmission = Transmission(name = "App transmission")
}