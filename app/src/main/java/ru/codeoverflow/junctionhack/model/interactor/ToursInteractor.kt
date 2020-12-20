package ru.codeoverflow.junctionhack.model.interactor

import ru.codeoverflow.junctionhack.entity.tours.UserRequest
import ru.codeoverflow.junctionhack.model.server.JunctionHackApi
import ru.codeoverflow.junctionhack.model.server.JunctionHackApiML

class ToursInteractor(
    private val apiMl: JunctionHackApiML,
    private val api: JunctionHackApi
) {

    suspend fun getActivitiesML(userId: String) =
        apiMl.getActivities(UserRequest(userId)).data?.data ?: listOf()

    suspend fun getActivities() =
        api.getActivities().data?.data ?: listOf()
}