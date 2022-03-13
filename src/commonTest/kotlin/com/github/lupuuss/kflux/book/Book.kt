package com.github.lupuuss.kflux.book

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.chapter.Chapter
import com.github.lupuuss.kflux.chapter.ChapterDescriptor
import com.github.lupuuss.kflux.norm.Dependency
import com.github.lupuuss.kflux.norm.EntityDescriptor

data class Book(
    val id: String,
    val title: String,
    val chapters: List<Chapter>
)

data class BookNorm(
    val id: String,
    val title: String,
    val chapterIds: List<String>
)

object BookDescriptor : EntityDescriptor<AppState, String, Book, BookNorm> {

    override val dependencies = listOf(Dependency(ChapterDescriptor, Book::chapters))

    override fun Book.normalize() = BookNorm(id, title, chapters.map(Chapter::id))

    override fun BookNorm.denormalize(state: AppState) = Book(id, title, ChapterDescriptor.resolve(state, chapterIds))

    override fun AppState.getNorm(id: String) = books[id]

    override fun AppState.getAllNorms() = books.values.toList()

    override fun storeNormalized(entities: List<BookNorm>) = BookStateAction.Store(entities)
}
