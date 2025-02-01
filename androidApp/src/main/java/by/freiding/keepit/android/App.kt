package by.freiding.keepit.android

import android.app.Application
import by.freiding.keepit.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            config = {
                androidContext(this@App)
                androidLogger()
            }
        )
    }
}