package com.example.testdraw.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    val characters: List<Character>,
    val currentPage: Int,
    val pageSize: Int,
    val totalCharacters: Int,
)
