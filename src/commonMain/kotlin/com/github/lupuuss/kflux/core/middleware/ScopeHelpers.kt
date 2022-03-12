package com.github.lupuuss.kflux.core.middleware

import com.github.lupuuss.kflux.core.scope.DispatchScope

inline fun DispatchScope<*>.consume() = Middleware.Status.Consumed

inline fun DispatchScope<*>.pass() = Middleware.Status.Passed