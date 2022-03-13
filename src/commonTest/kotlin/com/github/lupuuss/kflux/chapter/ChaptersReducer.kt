package com.github.lupuuss.kflux.chapter

import com.github.lupuuss.kflux.core.Action


fun chaptersReducer(chapters: Map<String, Chapter>, action: Action) = when(action) {
    is ChapterStateAction.Store -> chapters + action.chapters.associateBy(Chapter::id)
    else -> chapters
}