package com.github.lupuuss.kflux.book

import com.github.lupuuss.kflux.core.Action

fun booksReducer(books: Map<String, BookNorm>, action: Action) = when(action) {
    is BookStateAction.Store -> books + action.books.associateBy(BookNorm::id)
    else -> books
}