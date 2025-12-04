package com.example.fitlife.data.models

sealed class SubscriptionPlan(
    val name: String,
    val price: Double,
    val features: List<String>
) {
    object Basic : SubscriptionPlan(
        name = "Basico",
        price = 0.0,
        features = listOf(
            "Acceso Basico",
            "Sin Progreso",
            "Soporte limitado"
        )
    )

    object Plus : SubscriptionPlan(
        name = "Plus",
        price = 9990.0,
        features = listOf(
            "Mejor que el Basico",
            "Acceso personalizado a tu plan",
            "Avances de tu progreso",
            "Soporte ilimitado"
        )
    )

    object Pro : SubscriptionPlan(
        name = "Pro",
        price = 14990.0,
        features = listOf(
            "Everything in Plus",
            "One-on-one coaching",
            "Exclusive content and workshops",
            "Early access to new features"
        )
    )
}
