package se.braindome.urkraft.model

import java.util.UUID

data class MesoCycle(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val microCycles: List<MicroCycle> = emptyList()
)

