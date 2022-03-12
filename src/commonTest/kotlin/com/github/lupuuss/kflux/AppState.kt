package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.task.Task

data class AppState(val tasks: List<Task> = emptyList())