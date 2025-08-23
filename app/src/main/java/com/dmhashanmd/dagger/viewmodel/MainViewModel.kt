package com.dmhashanmd.dagger.viewmodel

import androidx.lifecycle.ViewModel
import com.dmhashanmd.dagger.model.Transmission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transmission: Transmission
): ViewModel(){
    val _transmission = MutableStateFlow(Transmission(""))

    init {
        _transmission.update { transmission }
    }
}