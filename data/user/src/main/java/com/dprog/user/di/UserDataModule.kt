package com.dprog.user.di

import com.dprog.user.repository.UserRepository
import com.dprog.user.repository.UserRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:user` component.
 */
val userDataModule: Module =
    module {
        single<UserRepository> { UserRepositoryImpl(get()) }
    }
