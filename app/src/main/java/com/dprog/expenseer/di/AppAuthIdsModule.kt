package com.dprog.expenseer.di

import android.app.Activity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appAuthIdsModule =
    module {
        // Provide a function that returns the default_web_client_id string
        single(named("webClientIdProvider")) {
            { activity: Activity ->
                activity.getString(com.dprog.expenseer.R.string.default_web_client_id)
            }
        }
    }
