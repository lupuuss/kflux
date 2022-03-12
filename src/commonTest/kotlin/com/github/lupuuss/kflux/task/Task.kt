package com.github.lupuuss.kflux.task

import kotlinx.datetime.LocalDateTime

data class Task(
    val id: String,
    val name: String,
    val isDone: Boolean,
    val modificationDate: LocalDateTime,
)