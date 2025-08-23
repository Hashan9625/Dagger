package com.dmhashanmd.dagger.model

import javax.inject.Inject

data class Car @Inject constructor(
    val engine: Engine,
)
