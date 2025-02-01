package by.freiding.keepit.di

import by.freiding.keepit.data.remote.parser.KsoupMetadataParser
import by.freiding.keepit.data.remote.parser.MetadataParser
import by.freiding.keepit.ui.screen.main.MainScreenViewModel
import by.freiding.keepit.ui.screen.root.RootViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    // Network
    single<MetadataParser> { KsoupMetadataParser() }

    // ViewModel
    viewModelOf(::RootViewModel)
    viewModelOf(::MainScreenViewModel)
}