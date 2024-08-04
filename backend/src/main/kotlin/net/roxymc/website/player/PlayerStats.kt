package net.roxymc.website.player

import net.roxymc.website.extension.asMongoFilter
import net.roxymc.website.mongoDB
import net.roxymc.website.player.stats.Stats
import org.bson.Document
import java.util.*

interface PlayerStats {
    val name: String
    val uniqueId: UUID
    val group: PlayerGroup

    fun complete(): CompletePlayerStats
}

fun fetchBasePlayersStats(): Map<UUID, PlayerStats> {
    return mongoDB.fetchToolsData()
        .find()
        .map { document ->
            val uniqueId = document.get("_id", UUID::class.java)
            val name = document.getString("_name")
            val extra = mongoDB.fetchLuckPermsUsers()
                .find(uniqueId.asMongoFilter())
                .first()

            uniqueId to BasePlayerStats(
                name,
                uniqueId,
                extra?.getList("permissions", Document::class.java)?.let { getGroup(it) } ?: PlayerGroup.DEFAULT
            )
        }
        .sortedBy { it.second.name }
        .toMap()
}

data class BasePlayerStats(
    override val name: String,
    override val uniqueId: UUID,
    override val group: PlayerGroup
) : PlayerStats {
    override fun complete(): CompletePlayerStats {
        return CompletePlayerStats(
            name,
            uniqueId,
            group,
            Stats.Type.entries.associateWith { it.fetch(uniqueId) }
        )
    }
}

data class CompletePlayerStats(
    override val name: String,
    override val uniqueId: UUID,
    override val group: PlayerGroup,
    val stats: Map<Stats.Type, Stats>? = null
) : PlayerStats {
    override fun complete(): CompletePlayerStats {
        return this
    }
}