package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.book.Book
import com.github.lupuuss.kflux.chapter.Chapter
import com.github.lupuuss.kflux.collection.BookCollection

object StubData {
    fun collections() = listOf(
        singleCollection("1"),
        singleCollection("2"),
        singleCollection("3"),
    )

    fun singleChapter(
        id: String = "1",
        title: String = "Chapter $id",
    ) = Chapter(id, title)

    fun singleBook(
        id: String = "1",
        title: String = "Book $id",
        chapters: List<Chapter> = listOf(singleChapter("1"), singleChapter("2"))
    ) = Book(id, title, chapters)

    fun singleCollection(
        id: String = "1",
        name: String = "Collection $id",
        books: List<Book> = listOf(singleBook("1"), singleBook("2"))
    ) = BookCollection(id, name, books)
}