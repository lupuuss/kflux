package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.collection.BookCollectionAction
import com.github.lupuuss.kflux.collection.BookCollectionDescriptor
import com.github.lupuuss.kflux.core.store.currentState
import com.github.lupuuss.kflux.data.StubData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AppStoreTest {

    @Test
    fun test() = runTest {
        val store = appStore(this)
        store.dispatch(BookCollectionAction.Load)
        advanceUntilIdle()
        assertEquals(
            StubData.collections(),
            BookCollectionDescriptor.resolve(store.currentState, store.currentState.collectionsOrder)
        )
    }
}