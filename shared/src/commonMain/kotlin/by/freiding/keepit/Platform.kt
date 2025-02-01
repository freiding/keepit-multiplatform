package by.freiding.keepit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform