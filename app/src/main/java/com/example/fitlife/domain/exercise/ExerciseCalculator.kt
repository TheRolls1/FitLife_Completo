package com.fitlife.app.domain.exercise

interface ExerciseCalculator {
    fun calculateCalories(durationMinutes: Int): Int
}
