package com.dmhashanmd.dagger

import android.app.Application
import com.dmhashanmd.dagger.contentProvider.DaggerParentComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    val parentComponent by lazy {
        DaggerParentComponent.create()
    }
}