package com.github.lupuuss.kflux.chapter

import com.github.lupuuss.kflux.core.Action

sealed class ChapterStateAction : Action {
    data class Store(val chapters: List<Chapter>) : ChapterStateAction()
}