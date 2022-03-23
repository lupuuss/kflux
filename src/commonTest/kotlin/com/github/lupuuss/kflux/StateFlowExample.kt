package com.github.lupuuss.kflux

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private val stateFlow = MutableStateFlow(1)

private suspend fun stateFlowExample(): Nothing = coroutineScope {
    stateFlow.value = 3
    stateFlow
        .map { (it + 2).toString() } // Flow<String>
        .stateIn(this) // State<Flow>  <--- suspend!!!
        .collect {

        }
}


private suspend fun stateFlowExample2(): Nothing = coroutineScope {
    stateFlow
        .map { (it + 2).toString() } // Flow<String>
        .stateIn(this, SharingStarted.Eagerly, "Initial state") // State<Flow>  <--- normal function
        .collect {

        }
}

