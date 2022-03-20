package com.github.lupuuss.kflux.collection

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.book.Book
import com.github.lupuuss.kflux.book.BookDescriptor
import com.github.lupuuss.kflux.norm.Dependency
import com.github.lupuuss.kflux.norm.EntityDescriptor

data class BookCollection(
    val id: String,
    val name: String,
    val books: List<Book>
)


data class BookCollectionNorm(
    val id: String,
    val name: String,
    val bookIds: List<String>
)

interface BookCollectionDescriptor : EntityDescriptor<AppState, String, BookCollection, BookCollectionNorm> {

    class Instance(private val bookDescriptor: BookDescriptor = BookDescriptor) : BookCollectionDescriptor {
        override val dependencies = listOf(Dependency(bookDescriptor, BookCollection::books))

        override fun BookCollection.normalize() = BookCollectionNorm(id, name, books.map(Book::id))

        override fun BookCollectionNorm.denormalize(state: AppState) = BookCollection(id, name, bookDescriptor.resolve(state, bookIds))

        override fun AppState.getNorm(id: String) = collections[id]

        override fun AppState.getAllNorms() = collections.values.toList()

        override fun storeNormalized(entities: List<BookCollectionNorm>) = BookCollectionStateAction.Store(entities)
    }

    companion object : BookCollectionDescriptor by Instance()
}