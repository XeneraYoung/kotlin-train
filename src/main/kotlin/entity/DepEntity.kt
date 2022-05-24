package entity

import kotlinx.serialization.Serializable

@Serializable
data class DepEntity(
    val id: Int,
    val name: String,
)
