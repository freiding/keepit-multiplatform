package by.freiding.keepit

import androidx.compose.runtime.Composable
import by.freiding.keepit.ui.screen.root.RootScreen
import by.freiding.keepit.ui.theme.ApplicationTheme
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import okio.FileSystem

@Composable
fun KeepItApp() {
    initCoilAsyncImageLoader()

    ApplicationTheme {
        RootScreen()
    }

}

@Composable
private fun initCoilAsyncImageLoader() {
    val context = LocalPlatformContext.current
    setSingletonImageLoaderFactory {
        val httpClient = HttpClient() {
            install(HttpTimeout) {
                connectTimeoutMillis = 30_000
                requestTimeoutMillis = 150_000
            }
        }
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory(httpClient)) // Add Ktor network fetcher factory
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache") // Set cache directory
                    .maxSizeBytes(512L * 1024 * 1024) // Set max size to 512MB
                    .build()
            }
            .build()
    }
}