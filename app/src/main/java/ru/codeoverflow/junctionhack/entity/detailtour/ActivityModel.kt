package ru.codeoverflow.junctionhack.entity.detailtour

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActivityModel(
    @SerializedName("id") val id: String?,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("date") val date: String?
) : Parcelable