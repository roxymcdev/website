package net.roxymc.website.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoCollection
import net.roxymc.website.mongoDB
import org.bson.Document
import org.bson.UuidRepresentation


class MongoDB(private val address: String, private val username: String, private val password: String) {
    lateinit var client: MongoClient
        private set

    fun connect() {
        check(!this::client.isInitialized) { "Already connected" }

        val options = MongoClientOptions.builder()
            .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)

        val credentials = MongoCredential.createCredential(
            username,
            "admin",
            password.toCharArray()
        )

        client = MongoClient(ServerAddress(address, 27017), credentials, options.build())
    }

    fun fetchLuckPermsUsers(): MongoCollection<Document> {
        return mongoDB.client.getDatabase("luckperms").getCollection("users")
    }

    fun fetchToolsData(): MongoCollection<Document> {
        return mongoDB.client.getDatabase("roxytools").getCollection("player_data")
    }

    fun fetchLangsData(): MongoCollection<Document> {
        return mongoDB.client.getDatabase("roxylangs").getCollection("player_data")
    }

    fun fetchFNaFData(): MongoCollection<Document> {
        return mongoDB.client.getDatabase("roxyfnaf").getCollection("player_data")
    }
}