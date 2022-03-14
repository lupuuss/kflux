package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.combine.plus
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.data.DataSourceLoader
import com.github.lupuuss.kflux.norm.NormalizeEntities
import com.github.lupuuss.kflux.thunk.chain
import com.github.lupuuss.kflux.thunk.thunkExec

object BookCollectionAction {

    object Load : DataSourceLoader<Unit, List<BookCollection>>(
        request = Unit,
        dataSource = DataSourceFactory::collections,
        onSuccess = { NormalizeEntities(BookCollectionDescriptor, it) chain thunkExec<AppState> { BookCollectionStateAction.SetOrder(it) } }
    )
}