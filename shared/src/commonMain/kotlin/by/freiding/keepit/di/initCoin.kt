package by.freiding.keepit.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null,
    vararg modules: Module
) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            *modules,
        )
    }
}