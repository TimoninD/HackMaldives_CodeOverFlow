package ru.codeoverflow.junctionhack.entity.detailtour

data class Ticket(
    val cityFrom: String,
    val timeFrom: String,
    val company: String,
    val cityTo: String,
    val timeTo: String,
    val price: Int
)