package com.dprog.auth.di

import com.dprog.auth.AuthService
import com.dprog.auth.FirebaseAuthService
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val authDataModule =
    module {
        single { FirebaseAuth.getInstance() }
        single<AuthService> { FirebaseAuthService(get()) }
    }
