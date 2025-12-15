package com.fitlife.app.domain.exercise

class CardioCalculator : ExerciseCalculator {

    override fun calculateCalories(durationMinutes: Int): Int {
        return durationMinutes * 8
    }
}
