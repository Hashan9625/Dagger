package com.dmhashanmd.dagger.module

import com.dmhashanmd.dagger.model.Engine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule
{
    @Provides
    @Singleton
    fun provideEngine(): Engine{
      return Engine("Petrol")
    }
}