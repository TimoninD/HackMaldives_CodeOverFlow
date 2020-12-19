package ru.codeoverflow.junctionhack.model.server

import retrofit2.http.Body
import retrofit2.http.POST
import ru.codeoverflow.junctionhack.entity.ResponseWrapper
import ru.codeoverflow.junctionhack.entity.login.LoginRequest
import ru.codeoverflow.junctionhack.entity.login.PhoneVerifyRequest

interface JunctionHackApi {

    @POST("users/signup")
    suspend fun signUp(@Body body: LoginRequest): ResponseWrapper<Boolean>

    @POST("users/verify")
    suspend fun verify(@Body body: PhoneVerifyRequest): ResponseWrapper<Boolean>
}