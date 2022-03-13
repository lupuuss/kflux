package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.core.Action

sealed class BookCollectionStateAction : Action {
    data class Store(val collections: List<BookCollectionNorm>): BookCollectionStateAction()
    data class SetOrder(val collections: List<BookCollection>): BookCollectionStateAction()
}