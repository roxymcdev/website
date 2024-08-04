package net.roxymc.website.player.stats

import com.google.gson.annotations.SerializedName
import java.util.*

interface Stats {
    enum class Type(private val fetchFunction: (UUID) -> Stats) {
        @SerializedName("general")
        GENERAL(::fetchGeneralStats),

        @SerializedName("fnaf")
        FNAF(::fetchFNaFStats),
        ;

        fun fetch(uniqueId: UUID): Stats {
            return fetchFunction.invoke(uniqueId)
        }
    }
}