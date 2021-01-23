package com.android.academy.fundamentals.app.workshop04

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object ConnectionChecker {
    // TCP/HTTP/DNS (depending on the port, 53=DNS, 80=HTTP, etc.)
    fun isOnline(): Boolean {
        return try {
            Socket().run {
                connect(InetSocketAddress("8.8.8.8", 53), 500)
                close()
            }
            true
        } catch (e: IOException) {
            false
        }
    }
}