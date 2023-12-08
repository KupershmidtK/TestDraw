package com.example.testdraw.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject


@Serializable
data class Character(
    val id:	Int,
    val name:	String,
    val images:	List<String>?,
    val debut: Debut? = null,
    val personal: Personal? = null,
    val family: Family? = null,
    val jutsu: List<String>? = null,
    val natureType: List<String>? = null,
    val uniqueTraits: List<String>? = null,
    val voiceActors: Voice? = null,
)

@Serializable
data class Personal (
    val species: String = ""
)

@Serializable
data class Debut(
    val novel: String = "",
    val movie: String = "",
    val manga: String = "",
    val anime: String = "",
    val game: String = "",
    val ova: String = "",
    val appearsIn: String = ""
)

@Serializable
data class Family(
    val father: String = "",
    val brother: String = "",
    @SerialName("adoptive brother") val abrother: String = "",
    @SerialName("adoptive father") val afather: String = "",
    val husband: String = "",
    @SerialName("incarnation with the god tree") val incarnation : String = "",
    @SerialName("depowered form") val depowered: String = "",
    val son: String = "",
    val nephew: String = "",
    @SerialName("adoptive son") val ason: String = ""
)


@Serializable(with = VoiceSerializer::class)
data class Voice (
    val japanese: List<String> = listOf(),
    val english: List<String> = listOf()
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Voice::class)
object VoiceSerializer : KSerializer<Voice> {
    override fun deserialize(decoder: Decoder): Voice {
        val json = ((decoder as JsonDecoder).decodeJsonElement() as JsonObject)
        return Voice(parseField("japanese", json), parseField("english", json),)
    }

    private fun parseField(field: String, json: JsonObject): List<String> {
        val info = json[field] ?: return emptyList()
        return try {
            listOf(Json.decodeFromString<String>(info.toString()))
        } catch (e: Exception) {
            (info as JsonArray).map { Json.decodeFromString<String>(it.toString()) }
        }
    }
}