package ru.codeoverflow.junctionhack.entity.detailtour

data class DetailTourModel(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val type: String = "Sights",
    val rating: Int = 3,
    val price: Int = 12000,
    val participants: String,
    val activitiesList: List<ActivityModel>
)

data class ActivityModel(
    val id: String = "",
    val image: String = "",
    val title: String,
    val description: String,
    val date: String
)