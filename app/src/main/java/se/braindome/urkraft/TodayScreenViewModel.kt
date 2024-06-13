package se.braindome.urkraft

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import se.braindome.urkraft.model.Exercise
import timber.log.Timber

class TodayScreenViewModel: ViewModel() {
    // private val _exercises = MutableLiveData<List<Exercise>>(emptyList())
    // val exercises: LiveData<List<Exercise>> = _exercises

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    private val _exerciseName = MutableLiveData("")
    val exerciseName: LiveData<String> = _exerciseName

    private val _sets = MutableLiveData(3)
    val sets: LiveData<Int> = _sets

    private val _reps = MutableLiveData(8)
    val reps: LiveData<Int> = _reps

    private val _weight = MutableLiveData(60f)
    val weight: LiveData<Float> = _weight

    private val _exerciseColor = MutableLiveData(Color.Red)
    val exerciseColor: LiveData<Color> = _exerciseColor

    private val _showColorPicker = MutableLiveData(false)
    val showColorPicker: LiveData<Boolean> = _showColorPicker

    fun updateExerciseName(name: String) {
        _exerciseName.value = name
    }

    fun updateSets(sets: Int) {
        _sets.value = sets
    }

    fun updateReps(reps: Int) {
        _reps.value = reps
    }

    fun updateWeight(weight: Float) {
        _weight.value = weight
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