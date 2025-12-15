package com.fitlife.app.ui.workout

import androidx.lifecycle.ViewModel
import com.fitlife.app.domain.exercise.ExerciseCalculator

class WorkoutViewModel : ViewModel() {

    fun calculateCalories(
        calculator: ExerciseCalculator,
        durationMinutes: Int
    ): Int {
        return calculator.calculateCalories(durationMinutes)
    }
}
