package com.app.memento

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform