package net.roxymc.website.database

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.UuidRepresentation


class MongoDB(private val address: String, private val username: String, private val password: String) {
    lateinit var client: MongoClient
        private set

    fun connect() {
        check(!this::client.isInitialized) { "Already connected" }

        client = MongoClients.create(
            MongoClientSettings.builder()
                .applyToClusterSettings { builder ->
                    builder.hosts(
                        listOf(
                            ServerAddress(address, 27017)
                        )
                    )
                }
                .credential(
                    MongoCredential.createCredential(
                        username,
                        "admin",
                        password.toCharArray()
                    )
                )
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
                .build()
        )
    }

    fun fetchLuckPermsUsers(): MongoCollection<Document> {
        return client.getDatabase("luckperms").getCollection("users")
    }

    fun fetchToolsData(): MongoCollection<Document> {
        return client.getDatabase("roxytools").getCollection("player_data")
    }

    fun fetchLangsData(): MongoCollection<Document> {
        return client.getDatabase("roxylangs").getCollection("player_data")
    }

    fun fetchFNaFData(): MongoCollection<Document> {
        return client.getDatabase("roxyfnaf").getCollection("player_data")
    }
}