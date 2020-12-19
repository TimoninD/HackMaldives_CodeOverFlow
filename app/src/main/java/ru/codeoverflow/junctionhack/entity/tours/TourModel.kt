package ru.codeoverflow.junctionhack.entity.tours

data class TourModel(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val type: String = "Sights",
    val rating: Int = 3,
    val price: Int = 12000
)