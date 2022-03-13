package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.DateProvider
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.data.DataSourceLoader
import com.github.lupuuss.kflux.kodein.di
import com.github.lupuuss.kflux.thunk.Thunk
import org.kodein.di.instance

object TaskAction {

    data class Complete(val id: String) : Thunk.Execute<AppState>({
        val dateProvider by di.instance<DateProvider>()
        val newTask = state.getTaskById(id).copy(isDone = true, modificationDate = dateProvider.get())
        dispatch(TaskStateAction.Replace(newTask))
    })

    object Load : DataSourceLoader<Unit, List<Task>>(
        request = Unit,
        dataSource = DataSourceFactory::tasks,
        onSuccess = TaskStateAction::SetAll,
    )
}

private fun AppState.getTaskById(id: String) = tasks.first { it.id == id }