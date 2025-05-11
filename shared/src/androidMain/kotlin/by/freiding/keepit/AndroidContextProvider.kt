package by.freiding.keepit

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


object AndroidContextProvider: KoinComponent {

    fun getApplicationContext(): Context {
        val ctx = get<Context>()
        return ctx;
    }
}