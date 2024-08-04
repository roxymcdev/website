package net.roxymc.website.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.Duration.Companion.seconds

fun Application.configureRateLimiting() {
    install(RateLimit) {
        register(RateLimitName("player-stats")) {
            rateLimiter(limit = 10, refillPeriod = 1.seconds)
        }
    }
}