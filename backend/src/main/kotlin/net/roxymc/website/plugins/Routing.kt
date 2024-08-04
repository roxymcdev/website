package net.roxymc.website.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import net.roxymc.website.routes.playerStatsRoutes

fun Application.configureRouting() {
    routing {
        playerStatsRoutes()
    }
}
