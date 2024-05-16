package se.braindome.urkraft.model

object Repository {
    fun getWorkouts(): List<Workout> {
        return listOf(
            Workout(
                name = "Chest Day",
                exercises = listOf(
                    Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50f),
                    Exercise(name = "Incline Bench Press", sets = 3, reps = 10, weight = 40f),
                    Exercise(name = "Dumbbell Flyes", sets = 3, reps = 10, weight = 20f),
                ),
                weekDay = "Tuesday"
            ),
            Workout(
                name = "Leg Day",
                exercises = listOf(
                    Exercise(name = "Squats", sets = 3, reps = 10, weight = 70f),
                    Exercise(name = "Leg Press", sets = 3, reps = 10, weight = 100f),
                    Exercise(name = "Calf Raises", sets = 3, reps = 15, weight = 30f),
                ),
                weekDay = "Thursday"
            ),
            Workout(
                name = "Deadlift Day",
                exercises = listOf(
                    Exercise(name = "Deadlift", sets = 3, reps = 10, weight = 100f),
                    Exercise(name = "Barbell Row", sets = 3, reps = 10, weight = 70f),
                    Exercise(name = "Lat Pulldown", sets = 3, reps = 10, weight = 50f),
                ),
                weekDay = "Saturday"
            ),
            Workout(
                name = "Shoulder Day",
                exercises = listOf(
                    Exercise(name = "Overhead Press", sets = 3, reps = 10, weight = 40f),
                    Exercise(name = "Lateral Raise", sets = 3, reps = 15, weight = 15f),
                    Exercise(name = "Front Raise", sets = 3, reps = 15, weight = 15f),
                ),
                weekDay = "Monday"
            )
            // Add more workouts as needed
        )
    }

    fun getExercises(): List<Exercise> {
        return listOf(
            Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50f),
            Exercise(name = "Incline Bench Press", sets = 3, reps = 10, weight = 40f),
            Exercise(name = "Dumbbell Flyes", sets = 3, reps = 10, weight = 20f),
            Exercise(name = "Squats", sets = 3, reps = 10, weight = 70f),
            Exercise(name = "Leg Press", sets = 3, reps = 10, weight = 100f),
            Exercise(name = "Calf Raises", sets = 3, reps = 15, weight = 30f),
            Exercise(name = "Deadlift", sets = 3, reps = 10, weight = 100f),
            Exercise(name = "Barbell Row", sets = 3, reps = 10, weight = 70f),
            Exercise(name = "Lat Pulldown", sets = 3, reps = 10, weight = 50f),
            Exercise(name = "Overhead Press", sets = 3, reps = 10, weight = 40f),
            Exercise(name = "Lateral Raise", sets = 3, reps = 15, weight = 15f),
            Exercise(name = "Front Raise", sets = 3, reps = 15, weight = 15f)
            // Add more exercises as needed
        )
    }

}