package net.roxymc.website.player.stats

import net.roxymc.website.extension.asMongoFilter
import net.roxymc.website.mongoDB
import java.util.*


fun fetchFNaFStats(uniqueId: UUID): FNaFStats {
    val data = mongoDB.fetchFNaFData()
        .find(uniqueId.asMongoFilter())
        .first()

    @Suppress("UNCHECKED_CAST")
    return FNaFStats(
        data?.getInteger("PLAYER_EXP"),
        data?.get("PLAYER_PLAYED") as? Map<String, Int>,
        data?.get("PLAYER_WINS") as? Map<String, Int>
    )
}

data class FNaFStats(val exp: Int?, val played: Map<String, Int>?, val wins: Map<String, Int>?) : Stats