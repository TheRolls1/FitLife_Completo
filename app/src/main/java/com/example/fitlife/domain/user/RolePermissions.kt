package com.example.fitlife.domain.user

fun canCreateRoutine(role: UserRole): Boolean {
    return role == UserRole.ADMIN || role == UserRole.COACH
}
