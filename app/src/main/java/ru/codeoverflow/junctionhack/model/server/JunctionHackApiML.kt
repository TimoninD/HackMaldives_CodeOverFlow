package ru.codeoverflow.junctionhack.model.server

import retrofit2.http.Body
import retrofit2.http.POST
import ru.codeoverflow.junctionhack.entity.DataWrapper
import ru.codeoverflow.junctionhack.entity.ResponseWrapper
import ru.codeoverflow.junctionhack.entity.detailtour.ActivityModel
import ru.codeoverflow.junctionhack.entity.tours.UserRequest

interface JunctionHackApiML {

    @POST("upload")
    suspend fun getActivities(@Body body: UserRequest): ResponseWrapper<DataWrapper<List<ActivityModel>>>
}