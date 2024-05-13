package se.braindome.urkraft.model

import java.util.UUID

data class Workout(
    val exercises: List<Exercise> = emptyList(),
    val id: String = UUID.randomUUID().toString(),
    val weekDay: String,
    val date: String = "",
)
