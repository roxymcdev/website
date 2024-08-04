package net.roxymc.website.extension

import org.bson.Document

fun Any.asMongoFilter(): Document {
    return Document("_id", this)
}