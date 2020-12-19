package ru.codeoverflow.junctionhack.entity.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: String
)