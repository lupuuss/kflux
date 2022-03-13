package com.github.lupuuss.kflux.book

import com.github.lupuuss.kflux.core.Action

sealed class BookStateAction : Action {
    data class Store(val books: List<BookNorm>) : BookStateAction()
}