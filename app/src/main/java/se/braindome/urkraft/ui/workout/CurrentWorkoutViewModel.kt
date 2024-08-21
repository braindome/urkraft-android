package se.braindome.urkraft.ui.workout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import se.braindome.urkraft.model.Exercise
import timber.log.Timber

data class WorkoutUiState(
    val exerciseName: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weight: Float = 0f,
    val exerciseColor: String = "#FFFFFF",
    val showColorPicker: Boolean = false
)

class CurrentWorkoutViewModel: ViewModel() {
    // private val _exercises = MutableLiveData<List<Exercise>>(emptyList())
    // val exercises: LiveData<List<Exercise>> = _exercises

    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises



    fun resetExerciseValues() {
        _uiState.value = WorkoutUiState()
    }

    fun updateExerciseName(name: String) {
        _uiState.value = _uiState.value.copy(exerciseName = name)
    }

    fun updateSets(sets: Int) {
        _uiState.value = _uiState.value.copy(sets = sets)
    }

    fun updateReps(reps: Int) {
        _uiState.value = _uiState.value.copy(reps = reps)
    }

    fun updateWeight(weight: Float) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun updateExerciseColor(color: String) {
        _uiState.value = _uiState.value.copy(exerciseColor = color)
    }

    fun addExerciseToList(exercise: Exercise) {
        val updatedList = _exercises.value.toMutableList()
        updatedList.add(exercise)
        _exercises.value = updatedList
        Timber.d("Added $exercise to list: $exercises")
    }

    fun removeExerciseFromList(exercise: Exercise) {
        val updatedList = _exercises.value.filter { it.id != exercise.id }
        _exercises.value = updatedList
    }
}