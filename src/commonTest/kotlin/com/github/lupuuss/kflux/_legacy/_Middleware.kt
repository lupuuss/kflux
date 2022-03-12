package com.github.lupuuss.kflux._legacy

import com.github.lupuuss.kflux.core.Action

typealias _DispatchFunction = (Action) -> Unit

typealias _Middleware<State> = (_DispatchFunction, () -> State?) -> (_DispatchFunction) -> _DispatchFunction