package se.braindome.urkraft.model

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val oneRepMaxes: List<OneRepMax> = emptyList(),
    val workouts: List<Workout> = emptyList(),
    val mesoCycles: List<MesoCycle> = emptyList(),
    val usingMetric: Boolean = true

)
