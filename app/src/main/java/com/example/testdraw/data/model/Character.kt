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
import java.lang.RuntimeException


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
    val rank: Rank? = null,
    val voiceActors: Voice? = null,
)

@Serializable
data class Rank (
    val ninjaRank: Map<String, String>
)

//----------------------------------------------
@Serializable(with = PersonalSerializer::class)
data class Personal (
    val birthdate: List<String>? = null,
    val sex: List<String>? = null,
    val height: Map<String,String>? = null,
    val weight: Map<String,String>? = null,
    val bloodType: List<String>? = null,
    val occupation: List<String>? = null,
    val affiliation: List<String>? = null,
    val team: List<String>? = null,
    val partner: List<String>? = null,
    val clan: List<String>? = null,
    val species: List<String>? = null,
    val status: List<String>? = null,
    val kekkeiGenkai: List<String>? = null,
    val classification: List<String>? = null,
    val jinchūriki: List<String>? = null,
    val titles: List<String>? = null,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Personal::class)
object PersonalSerializer : KSerializer<Personal?> {
    override fun deserialize(decoder: Decoder): Personal? {
        val json = try {
            ((decoder as JsonDecoder).decodeJsonElement() as JsonObject)
        } catch (e: RuntimeException) {
            return null
        }

        return Personal(
            birthdate = json.fieldToList("birthdate"),
            sex = json.fieldToList("english"),
            bloodType = json.fieldToList("bloodType"),
            occupation = json.fieldToList("occupation"),
            affiliation = json.fieldToList("affiliation"),
            team = json.fieldToList("team"),
            partner = json.fieldToList("partner"),
            clan = json.fieldToList("clan"),
            species = json.fieldToList("species"),
            status = json.fieldToList("status"),
            kekkeiGenkai = json.fieldToList("kekkeiGenkai"),
            classification = json.fieldToList("classification"),
            jinchūriki = json.fieldToList("jinchūriki"),
            titles = json.fieldToList("titles"),
        )
    }
}

//-----------------------------------------------
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
        return Voice(
            json.fieldToList( "japanese"),
            json.fieldToList("english"))
    }
}

fun JsonObject.fieldToList(field: String): List<String> {
    val info = this[field] ?: return emptyList()
    return try {
        listOf(Json.decodeFromString<String>(info.toString()))
    } catch (e: Exception) {
        (info as JsonArray).map { Json.decodeFromString<String>(it.toString()) }
    }
}