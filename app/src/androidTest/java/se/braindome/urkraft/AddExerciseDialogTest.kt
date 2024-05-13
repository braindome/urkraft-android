package se.braindome.urkraft

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import se.braindome.urkraft.model.Exercise

@RunWith(AndroidJUnit4::class)
class AddExerciseDialogUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun exercise_fields_are_updated_correctly() {
        composeTestRule.setContent {
            AddExerciseDialog(
                onExerciseAdded = {},
                onDismissRequest = {}
            )
        }

        composeTestRule.onNodeWithText("Name").performTextInput("Updated Exercise")
        composeTestRule.onNodeWithText("Sets").performTextInput("4")
        composeTestRule.onNodeWithText("Reps").performTextInput("12")
        composeTestRule.onNodeWithText("Weight").performTextInput("20")

        composeTestRule.onNodeWithText("Name").assertTextEquals("Updated Exercise")
        composeTestRule.onNodeWithText("Sets").assertTextEquals("4")
        composeTestRule.onNodeWithText("Reps").assertTextEquals("12")
        composeTestRule.onNodeWithText("Weight").assertTextEquals("20")
    }

    @Test
    fun onExerciseAdded_is_called_when_Add_button_is_clicked() {
        var addedExercise: Exercise? = null
        composeTestRule.setContent {
            AddExerciseDialog(
                onExerciseAdded = { addedExercise = it },
                onDismissRequest = {}
            )
        }

        composeTestRule.onNodeWithText("Name").performTextInput("Test Exercise")
        composeTestRule.onNodeWithText("Sets").performTextInput("3")
        composeTestRule.onNodeWithText("Reps").performTextInput("10")
        composeTestRule.onNodeWithText("Weight").performTextInput("15")

        composeTestRule.onNodeWithText("Add").performClick()

        assert(addedExercise == Exercise(name = "Test Exercise", sets = 3, reps = 10, weight = 15f))
    }

    @Test
    fun onDismissRequest_is_called_when_Cancel_button_is_clicked() {
        var isDismissed = false
        composeTestRule.setContent {
            AddExerciseDialog(
                onExerciseAdded = {},
                onDismissRequest = { isDismissed = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assert(isDismissed)
    }
}