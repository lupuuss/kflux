package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.task.Task

interface DataSourceFactory {
    fun tasks(): DataSource<Unit, List<Task>>

    companion object Default : DataSourceFactory {
        override fun tasks(): DataSource<Unit, List<Task>> = TaskDataSource()
    }
}