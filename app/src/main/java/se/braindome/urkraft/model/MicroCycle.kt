package se.braindome.urkraft.model

import java.util.UUID

data class MicroCycle(
    val weekNumber: Int,
    val workouts: Map<String, Workout> = emptyMap(),
    val id: String = UUID.randomUUID().toString(),
)
