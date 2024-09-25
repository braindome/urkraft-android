package se.braindome.urkraft.room

import androidx.room.Database
import androidx.room.RoomDatabase
import se.braindome.urkraft.model.Exercise

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}