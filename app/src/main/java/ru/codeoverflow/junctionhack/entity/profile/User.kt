package ru.codeoverflow.junctionhack.entity.profile

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String?,
    @SerializedName("interests") val interests: List<String>?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("photo") val photo: String?,
    @SerializedName("location") val location: Location?
)

data class Location(
    @SerializedName("address") val address: String?
)