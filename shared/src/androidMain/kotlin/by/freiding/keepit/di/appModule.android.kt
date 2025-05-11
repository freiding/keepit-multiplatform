package by.freiding.keepit.di

import by.freiding.keepit.data.local.database.KeepItDatabase
import by.freiding.keepit.data.local.database.getKeepItDatabase
import by.freiding.keepit.database.getKeepItDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun sharedAppModule() = module {
    single<KeepItDatabase>{ getKeepItDatabase(getKeepItDatabaseBuilder(androidContext())) }
}