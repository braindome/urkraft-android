package se.braindome.urkraft.model

import java.util.UUID

data class Exercise(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Float
)
