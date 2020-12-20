package ru.codeoverflow.junctionhack.model.interactor

import ru.codeoverflow.junctionhack.model.server.JunctionHackApi

class ToursInteractor(private val api: JunctionHackApi) {

    suspend fun getActivities() = api.getActivities().data?.data ?: listOf()
}