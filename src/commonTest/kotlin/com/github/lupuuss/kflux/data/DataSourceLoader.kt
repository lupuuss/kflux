package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.kodein.di
import org.kodein.di.direct
import org.kodein.di.instance

abstract class DataSourceLoader<Request, Response>(
    private val request: Request,
    private val dataSource: (DataSourceFactory) -> DataSource<Request, Response>,
    private val onSuccess: (Response) -> Action,
    private val onError: ((Throwable) -> Action)? = null
) : DataLoader<Response, AppState>() {

    override suspend fun DispatchScope<AppState>.load(): Response = di.direct
        .instance<DataSourceFactory>()
        .let(dataSource)
        .get(request)

    override fun onSuccess(data: Response): Action? = onSuccess.invoke(data)

    override fun onError(error: Throwable): Action? = onError?.invoke(error)
}