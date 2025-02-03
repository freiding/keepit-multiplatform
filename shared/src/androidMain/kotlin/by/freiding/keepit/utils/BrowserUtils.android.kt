package by.freiding.keepit.utils

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import by.freiding.keepit.AndroidContextProvider


actual fun openUrl(url: String): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    return try {
        val ctx = AndroidContextProvider.getApplicationContext()
        startActivity(ctx, intent, null)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false // Handle failure to open URL
    }
}