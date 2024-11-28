package se.braindome.urkraft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents an exercise entity in the Room database.
 *
 * @property id The unique identifier for the exercise.
 * @property name The name of the exercise.
 * @property sets The number of sets for the exercise.
 * @property reps The number of repetitions per set.
 * @property weight The weight used for the exercise.
 * @property color The color tag associated with the exercise.
 */

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "exercise_name") val name: String,
    @ColumnInfo(name = "sets") val sets: Int,
    @ColumnInfo(name = "reps") val reps: Int,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "color_tag") val color: String = "#FFFFFF"
)
