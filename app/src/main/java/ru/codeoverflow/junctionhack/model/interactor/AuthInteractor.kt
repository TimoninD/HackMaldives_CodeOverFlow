package ru.codeoverflow.junctionhack.model.interactor

import ru.codeoverflow.junctionhack.entity.login.LoginRequest
import ru.codeoverflow.junctionhack.entity.login.PhoneVerifyRequest
import ru.codeoverflow.junctionhack.entity.profile.User
import ru.codeoverflow.junctionhack.model.server.JunctionHackApi

class AuthInteractor(private val api: JunctionHackApi) {

    suspend fun signIn(phone: String) = api.signUp(LoginRequest((phone))).status

    suspend fun verify(phone: String, code: String) =
        api.verify(PhoneVerifyRequest(phone, code)).token

    suspend fun getUser() = api.getCurrentUser().data?.data

    suspend fun updateUser(request: User?) = api.updateUser(request?.id, request).data?.data
}