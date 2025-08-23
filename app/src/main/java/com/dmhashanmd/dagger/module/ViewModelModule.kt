package com.dmhashanmd.dagger.module

import com.dmhashanmd.dagger.model.Transmission
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideTransmission(): Transmission {
        return Transmission("Viewmodel transmission")
    }

}