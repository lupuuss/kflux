package com.github.lupuuss.kflux.book.view

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.book.Book
import com.github.lupuuss.kflux.book.BookDescriptor
import com.github.lupuuss.kflux.core.store.Store
import com.github.lupuuss.kflux.mvvm.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.random.Random

class BooksViewModel(
    store: Store<AppState>,
    coroutineScope: CoroutineScope
) : BooksContract.ViewModel, BaseViewModel<AppState>(store, coroutineScope) {

    override val books = store.state
        .mapState(::toContract)
        .common()

    override fun navigateToBook(item: BooksContract.BookItem) {
        // store.dispatch(BookAction.View(item.id))
    }

    private fun toContract(state: AppState) = BookDescriptor
        .resolve(state, state.books.keys.toList())
        .map(::toItem)

    private fun toItem(book: Book) = book.run {
        BooksContract.BookItem(id, title, "Ordinal 1")
    }
}