package com.github.lupuuss.kflux.data

interface DataSource<Request, Response> {
    suspend fun get(request: Request): Response
}