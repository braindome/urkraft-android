package se.braindome.urkraft.model

object Repository {
    fun getWorkouts(): List<Workout> {
        return listOf(
            Workout(
                name = "Chest Day",
                exercises = listOf(
                    Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50.0),
                    Exercise(name = "Incline Bench Press", sets = 3, reps = 10, weight = 40.0),
                    Exercise(name = "Dumbbell Flyes", sets = 3, reps = 10, weight = 20.0),
                ),
                weekDay = "Tuesday"
            ),
            Workout(
                name = "Leg Day",
                exercises = listOf(
                    Exercise(name = "Squats", sets = 3, reps = 10, weight = 70.0),
                    Exercise(name = "Leg Press", sets = 3, reps = 10, weight = 100.0),
                    Exercise(name = "Calf Raises", sets = 3, reps = 15, weight = 30.0),
                ),
                weekDay = "Thursday"
            ),
            Workout(
                name = "Deadlift Day",
                exercises = listOf(
                    Exercise(name = "Deadlift", sets = 3, reps = 10, weight = 100.0),
                    Exercise(name = "Barbell Row", sets = 3, reps = 10, weight = 70.0),
                    Exercise(name = "Lat Pulldown", sets = 3, reps = 10, weight = 50.0),
                ),
                weekDay = "Saturday"
            ),
            Workout(
                name = "Shoulder Day",
                exercises = listOf(
                    Exercise(name = "Overhead Press", sets = 3, reps = 10, weight = 40.0),
                    Exercise(name = "Lateral Raise", sets = 3, reps = 15, weight = 15.0),
                    Exercise(name = "Front Raise", sets = 3, reps = 15, weight = 15.0),
                ),
                weekDay = "Monday"
            )
            // Add more workouts as needed
        )
    }

    fun getExercises(): List<Exercise> {
        return listOf(
            Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50.0),
            Exercise(name = "Incline Bench Press", sets = 3, reps = 10, weight = 40.0),
            Exercise(name = "Dumbbell Flyes", sets = 3, reps = 10, weight = 20.0),
            Exercise(name = "Squats", sets = 3, reps = 10, weight = 70.0),
            Exercise(name = "Leg Press", sets = 3, reps = 10, weight = 100.0),
            Exercise(name = "Calf Raises", sets = 3, reps = 15, weight = 30.0),
            Exercise(name = "Deadlift", sets = 3, reps = 10, weight = 100.0),
            Exercise(name = "Barbell Row", sets = 3, reps = 10, weight = 70.0),
            Exercise(name = "Lat Pulldown", sets = 3, reps = 10, weight = 50.0),
            Exercise(name = "Overhead Press", sets = 3, reps = 10, weight = 40.0),
            Exercise(name = "Lateral Raise", sets = 3, reps = 15, weight = 15.0),
            Exercise(name = "Front Raise", sets = 3, reps = 15, weight = 15.0)
        )
    }

    fun getFewerExercises(): List<Exercise> {
        return listOf(
            Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50.0),
            Exercise(name = "Incline Bench Press", sets = 3, reps = 10, weight = 40.0),
            Exercise(name = "Dumbbell Flyes", sets = 3, reps = 10, weight = 20.0),
            Exercise(name = "Squats", sets = 3, reps = 10, weight = 70.0),
            Exercise(name = "Leg Press", sets = 3, reps = 10, weight = 100.0),
        )
    }

}