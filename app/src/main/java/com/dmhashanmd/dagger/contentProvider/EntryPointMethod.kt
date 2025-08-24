package com.dmhashanmd.dagger.contentProvider

import com.dmhashanmd.dagger.model.Engine
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface EntryPointMethod {
    fun getEngine(): Engine
}