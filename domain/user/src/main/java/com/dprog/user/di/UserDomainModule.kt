package com.dprog.user.di

import com.dprog.user.usecase.CreateUserUseCase
import com.dprog.user.usecase.LoginUserUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `domain:user` component.
 *
 * User‑related business logic will eventually reside in this module.
 * For now, the module is empty because no use cases have been
 * implemented. Populate it with `factory` or `single` definitions
 * when user domain services are added.
 */
val userDomainModule: Module =
    module {
     single { CreateUserUseCase(get()) }
     single { LoginUserUseCase(get()) }
    }
