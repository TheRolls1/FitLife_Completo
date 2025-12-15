package com.example.fitlife.domain.user

object DefaultCredentials {

    val credentials = mapOf(
        UserRole.ADMIN to Pair("admin@fitlife.com", "admin123"),
        UserRole.COACH to Pair("coach@fitlife.com", "coach123"),
        UserRole.USER to Pair("user@fitlife.com", "user123"),
        UserRole.GUEST to Pair("guest@fitlife.com", "guest123")
    )
}
