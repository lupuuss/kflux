package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.core.Action

fun bookCollectionOrderReducer(order: List<String>, action: Action) = when(action) {
    is BookCollectionStateAction.SetOrder -> action.collections.map(BookCollection::id)
    else -> order
}