package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.collection.BookCollection
import kotlinx.coroutines.delay

class BookCollectionsDataSource(
    private val collections: List<BookCollection> = StubData.collections()
) : DataSource<Unit, List<BookCollection>> {
    override suspend fun get(request: Unit): List<BookCollection> {
        delay(1000)
        return collections
    }
}