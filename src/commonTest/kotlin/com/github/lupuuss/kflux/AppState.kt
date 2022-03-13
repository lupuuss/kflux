package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.book.BookNorm
import com.github.lupuuss.kflux.chapter.Chapter
import com.github.lupuuss.kflux.collection.BookCollectionNorm

data class AppState(
    val chapters: Map<String, Chapter> = emptyMap(),
    val books: Map<String, BookNorm> = emptyMap(),
    val collections: Map<String, BookCollectionNorm> = emptyMap(),
    val collectionsOrder: List<String> = emptyList()
)