package net.roxymc.website.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCORS() {
    install(CORS) {
        anyHost()
        exposeHeader("X-RateLimit-Limit")
        exposeHeader("X-RateLimit-Remaining")
        exposeHeader("X-RateLimit-Reset")
        exposeHeader("X-Total-Count")
    }
}