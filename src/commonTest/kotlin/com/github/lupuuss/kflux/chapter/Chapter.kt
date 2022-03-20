package com.github.lupuuss.kflux.chapter

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.norm.PureEntityDescriptor

data class Chapter(
    val id: String,
    val title: String
)

interface ChapterDescriptor : PureEntityDescriptor<AppState, String, Chapter> {

    companion object : ChapterDescriptor {
        override fun storeNormalized(entities: List<Chapter>): Action = ChapterStateAction.Store(entities)

        override fun AppState.getNorm(id: String): Chapter? = chapters[id]

        override fun AppState.getAllNorms(): List<Chapter> = chapters.values.toList()
    }
}