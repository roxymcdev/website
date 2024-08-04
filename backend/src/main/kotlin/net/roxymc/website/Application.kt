package net.roxymc.website

import io.github.cdimascio.dotenv.Dotenv
import io.ktor.server.application.*
import net.roxymc.website.database.MongoDB
import net.roxymc.website.player.PlayerStats
import net.roxymc.website.player.fetchBasePlayersStats
import net.roxymc.website.plugins.configureCORS
import net.roxymc.website.plugins.configureRateLimiting
import net.roxymc.website.plugins.configureRouting
import net.roxymc.website.plugins.configureSerialization
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration.Companion.minutes

private val dotenv = Dotenv.configure().ignoreIfMissing().load()

val mongoDB = MongoDB(
    dotenv["DATABASE_ADDRESS"],
    dotenv["DATABASE_USERNAME"],
    dotenv["DATABASE_PASSWORD"]
)
lateinit var playersStats: MutableMap<UUID, PlayerStats>
    private set

fun Application.module() {
    mongoDB.connect()

    fixedRateTimer(daemon = true, period = 5.minutes.inWholeMilliseconds) {
        log.info("Fetching player stats...")
        playersStats = fetchBasePlayersStats().toMutableMap()
    }

    configureCORS()
    configureRateLimiting()
    configureRouting()
    configureSerialization()
}
