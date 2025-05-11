package by.freiding.keepit.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openUrl(url: String): Boolean {
    val nsUrl = NSURL.URLWithString(url)
    return UIApplication.sharedApplication().openURL(nsUrl!!)
}