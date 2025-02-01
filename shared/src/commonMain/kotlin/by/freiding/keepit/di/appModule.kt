package by.freiding.keepit.di

import by.freiding.keepit.data.remote.parser.KsoupMetadataParser
import by.freiding.keepit.data.remote.parser.MetadataParser
import by.freiding.keepit.domain.usecase.links.FetchPageDataByLinkUseCase
import by.freiding.keepit.domain.usecase.links.SavePageDataToStorageUseCase
import by.freiding.keepit.ui.screen.links.add.AddLinkScreenViewModel
import by.freiding.keepit.ui.screen.main.MainScreenViewModel
import by.freiding.keepit.ui.screen.root.RootViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    // Network
    single<MetadataParser> { KsoupMetadataParser() }

    // UseCase
    singleOf(::FetchPageDataByLinkUseCase)
    singleOf(::SavePageDataToStorageUseCase)

    // ViewModel
    viewModelOf(::RootViewModel)
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::AddLinkScreenViewModel)
}