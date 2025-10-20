package com.dprog.database.di

import androidx.room.Room
import com.dprog.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule =
    module {
        single {
            Room
                .databaseBuilder(
                    androidContext(),
                    AppDatabase::class.java,
                    "expenseer.db",
                ).build()
        }
        single { get<AppDatabase>().transactionDao() }
        single { get<AppDatabase>().categoryDao() }
        single { get<AppDatabase>().userDao() }
    }
