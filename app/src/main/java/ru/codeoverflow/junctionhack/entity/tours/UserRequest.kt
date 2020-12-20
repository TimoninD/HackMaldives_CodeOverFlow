package ru.codeoverflow.junctionhack.entity.tours

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("userId") val userId: String
)