package com.github.lupuuss.kflux.core.context

object EmptyDispatchContext : DispatchContext {

    override fun <T : DispatchContext.Element> find(key: DispatchContext.Key<T>): T? = null

    override fun <T : DispatchContext.Element> get(key: DispatchContext.Key<T>): T = throw MissingContextElementException(key)

    override fun plus(context: DispatchContext) = context

    override fun split(): List<DispatchContext.Element> = emptyList()
}