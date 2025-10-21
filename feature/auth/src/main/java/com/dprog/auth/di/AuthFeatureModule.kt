package com.dprog.auth.di

import android.app.Activity
import androidx.credentials.CredentialManager
import com.dprog.auth.google.GoogleCredentialInteractor
import com.dprog.auth.navigation.AuthNavGraph
import com.dprog.auth.ui.AuthViewModel
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authFeatureModule: Module =
    module {
        single<FeatureNavGraph>(named("auth")) { AuthNavGraph() }

        // get the provider from app DI
        single {
            val webClientIdProvider = get<(Activity) -> String>(named("webClientIdProvider"))
            GoogleCredentialInteractor(
                credentialManagerFactory = { activity: Activity -> CredentialManager.create(activity) },
                webClientIdProvider = webClientIdProvider,
            )
        }

        viewModel { AuthViewModel(get(), get()) }
    }
