package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.book.booksReducer
import com.github.lupuuss.kflux.chapter.chaptersReducer
import com.github.lupuuss.kflux.collection.bookCollectionOrderReducer
import com.github.lupuuss.kflux.collection.bookCollectionReducer
import com.github.lupuuss.kflux.core.Action

fun appReducer(state: AppState, action: Action) = with(state) {
    AppState(
        chapters = chaptersReducer(chapters, action),
        books = booksReducer(books, action),
        collections = bookCollectionReducer(collections, action),
        collectionsOrder = bookCollectionOrderReducer(collectionsOrder, action)
    )
}