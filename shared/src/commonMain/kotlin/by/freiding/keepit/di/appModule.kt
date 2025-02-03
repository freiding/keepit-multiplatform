package by.freiding.keepit.di

import by.freiding.keepit.data.local.database.KeepItDatabase
import by.freiding.keepit.data.local.database.dao.StoredLinksDao
import by.freiding.keepit.data.remote.parser.KsoupMetadataParser
import by.freiding.keepit.data.remote.parser.MetadataParser
import by.freiding.keepit.domain.converter.StoredLinksConverter
import by.freiding.keepit.domain.converter.impl.StoredLinksConverterImpl
import by.freiding.keepit.domain.usecase.links.FetchPageDataByLinkUseCase
import by.freiding.keepit.domain.usecase.links.FetchStoredLinksListUseCase
import by.freiding.keepit.domain.usecase.links.SavePageDataToStorageUseCase
import by.freiding.keepit.ui.screen.links.add.AddLinkScreenViewModel
import by.freiding.keepit.ui.screen.links.list.StoredLinksListScreenViewModel
import by.freiding.keepit.ui.screen.main.MainScreenViewModel
import by.freiding.keepit.ui.screen.root.RootViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<StoredLinksDao>{ get<KeepItDatabase>().storedLinksDao }

    // Network
    single<MetadataParser> { KsoupMetadataParser() }

    // Converter
    singleOf<StoredLinksConverter>(::StoredLinksConverterImpl)

    // UseCase
    singleOf(::FetchPageDataByLinkUseCase)
    singleOf(::SavePageDataToStorageUseCase)
    singleOf(::FetchStoredLinksListUseCase)

    // ViewModel
    viewModelOf(::RootViewModel)
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::AddLinkScreenViewModel)
    viewModelOf(::StoredLinksListScreenViewModel)
}.plus(sharedAppModule())

expect fun sharedAppModule(): Module