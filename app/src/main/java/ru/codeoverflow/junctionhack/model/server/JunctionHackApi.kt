package ru.codeoverflow.junctionhack.model.server

import retrofit2.http.*
import ru.codeoverflow.junctionhack.entity.DataWrapper
import ru.codeoverflow.junctionhack.entity.ResponseWrapper
import ru.codeoverflow.junctionhack.entity.detailtour.ActivityModel
import ru.codeoverflow.junctionhack.entity.login.LoginRequest
import ru.codeoverflow.junctionhack.entity.login.PhoneVerifyRequest
import ru.codeoverflow.junctionhack.entity.profile.User

interface JunctionHackApi {

    @POST("users/signup")
    suspend fun signUp(@Body body: LoginRequest): ResponseWrapper<Boolean>

    @POST("users/verify")
    suspend fun verify(@Body body: PhoneVerifyRequest): ResponseWrapper<Boolean>

    @GET("users/me")
    suspend fun getCurrentUser(): ResponseWrapper<DataWrapper<User>>

    @PATCH("users/{id}")
    suspend fun updateUser(
        @Path("id") id: String?,
        @Body body: User?
    ): ResponseWrapper<DataWrapper<User>>

    @GET("activitites")
    suspend fun getActivities(): ResponseWrapper<DataWrapper<List<ActivityModel>>>
}