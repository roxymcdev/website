package net.roxymc.website.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.roxymc.website.playersStats
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun Route.playerStatsRoutes() {
    rateLimit(RateLimitName("player-stats")) {
        playersStatsListRoute()
        playerStatsByUniqueIdRoute()
    }
}

private fun Route.playersStatsListRoute() {
    get("/player-stats") {
        val search = call.request.queryParameters["search"] ?: ""
        val limit = max(min(call.request.queryParameters["limit"]?.toIntOrNull() ?: 16, 16), 0)
        val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

        if (offset >= playersStats.size) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val list = playersStats.values
            .filter { it.name.startsWith(search, ignoreCase = true) }

        call.response.header(HttpHeaders.XTotalCount, playersStats.values.size)
        call.respond(
            list.subList(offset, min(offset + limit, list.size))
        )
    }
}

private fun Route.playerStatsByUniqueIdRoute() {
    get("/player-stats/{player}") {
        try {
            val player = UUID.fromString(call.parameters["player"]!!)

            val stats = playersStats.computeIfPresent(player) { _, stats -> stats.complete() }
            if (stats == null) return@get

            call.respond(stats)
        } catch (_: Exception) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}