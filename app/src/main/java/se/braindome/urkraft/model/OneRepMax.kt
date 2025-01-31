package se.braindome.urkraft.model

data class OneRepMax(
    val exerciseId: String,
    val oneRepMax: Double
) {
    fun estimateOneRepMax(weight: Double, reps: Int): Double {
        return weight * (36 / (37 - reps))
    }
}
