package com.dprog.network.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:network` component.
 *
 * Networking clients and interceptors should be registered here
 * eventually. Currently, the module is empty because no network
 * implementation is provided. Keeping the module in place makes it
 * straightforward to introduce HTTP clients or API services later.
 */
val networkModule: Module = module {
    // Define network dependencies (e.g. OkHttp, Retrofit) here.
}