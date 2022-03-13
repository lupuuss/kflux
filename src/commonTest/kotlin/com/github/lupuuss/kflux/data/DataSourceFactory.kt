package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.collection.BookCollection

interface DataSourceFactory {
    fun collections(): DataSource<Unit, List<BookCollection>>

    companion object Default : DataSourceFactory {
        override fun collections(): DataSource<Unit, List<BookCollection>> = BookCollectionsDataSource()
    }
}