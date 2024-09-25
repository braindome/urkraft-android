package se.braindome.urkraft.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import se.braindome.urkraft.model.Exercise

@Dao
interface ExerciseDao {
    /**
     * Retrieves all exercises from the database.
     *
     * @return A list of all Exercise objects in the database.
     */
    @Query("SELECT * FROM exercises")
    fun getAll(): List<Exercise>

    /**
     * Retrieves an exercise by its ID.
     *
     * @param id The unique identifier of the exercise.
     * @return The Exercise object with the specified ID.
     */
    @Query("SELECT * FROM exercises WHERE id = :id")
    fun loadById(id: String): Exercise

    /**
     * Inserts multiple exercises into the database.
     *
     * @param exercises The Exercise objects to be inserted.
     */
    @Insert
    fun insertAll(vararg exercises: Exercise)

    /**
     * Deletes an exercise from the database.
     *
     * @param exercise The Exercise object to be deleted.
     */
    @Delete
    fun delete(exercise: Exercise)
}