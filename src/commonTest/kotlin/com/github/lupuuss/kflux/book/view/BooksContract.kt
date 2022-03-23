package com.github.lupuuss.kflux.book.view

import com.github.lupuuss.kflux.mvvm.CStateFlow
import kotlinx.coroutines.flow.StateFlow

interface BooksContract {

    interface ViewModel {
        val books: StateFlow<List<BookItem>>

        fun navigateToBook(item: BookItem)
    }

    data class BookItem(
        val id: String,
        val title: String,
        val ordinalTitle: Any,
    )
}