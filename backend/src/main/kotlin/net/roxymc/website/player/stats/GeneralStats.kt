package net.roxymc.website.player.stats

import net.roxymc.website.extension.asMongoFilter
import net.roxymc.website.mongoDB
import java.util.*

fun fetchGeneralStats(uniqueId: UUID): GeneralStats {
    val toolsData = mongoDB.fetchToolsData()
        .find(uniqueId.asMongoFilter())
        .first()
    val langsData = mongoDB.fetchLangsData()
        .find(uniqueId.asMongoFilter())
        .first()

    return GeneralStats(toolsData?.getDouble("PLAYER_BALANCE"), langsData?.getString("PLAYER_LANGUAGE"))
}

data class GeneralStats(val balance: Double?, val language: String?) : Stats