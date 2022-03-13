package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.core.Action

fun bookCollectionReducer(collections: Map<String, BookCollectionNorm>, action: Action) = when(action) {
    is BookCollectionStateAction.Store -> collections + action.collections.associateBy(BookCollectionNorm::id)
    else -> collections
}