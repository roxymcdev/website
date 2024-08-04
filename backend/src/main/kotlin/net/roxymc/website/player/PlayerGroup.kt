package net.roxymc.website.player

import org.bson.Document

fun getGroup(perms: Iterable<Document>): PlayerGroup {
    return perms
        .filter { it.getBoolean("value") && it.getString("key").startsWith("group.") }
        .map { it.getString("key").substringAfter("group.") }
        .map { group ->
            try {
                PlayerGroup.valueOf(group.uppercase())
            } catch (_: IllegalArgumentException) {
                PlayerGroup.DEFAULT
            }
        }
        .minBy { it.ordinal }
}

enum class PlayerGroup {
    OWNER,
    ADMIN,
    MOD,
    SUPPORT,
    ROXY,
    MVP,
    VIP,
    DEFAULT
}