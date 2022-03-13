package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.combine.plus
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.data.DataSourceLoader
import com.github.lupuuss.kflux.norm.NormalizeEntities

object BookCollectionAction {

    object Load : DataSourceLoader<Unit, List<BookCollection>>(
        request = Unit,
        dataSource = DataSourceFactory::collections,
        onSuccess = { NormalizeEntities(BookCollectionDescriptor, it) + BookCollectionStateAction.SetOrder(it) }
    )
}