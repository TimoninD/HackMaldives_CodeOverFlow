package ru.codeoverflow.junctionhack.entity.interests

open class BaseInterest

data class Interest(
    val imageId: Int,
    val name: String
) : BaseInterest()

data class InterestMore(
    val backgroundId: Int,
    val name: String,
    val textColorId: Int,
    val showName: String
) : BaseInterest()