package ru.codeoverflow.junctionhack.entity.tours

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.codeoverflow.junctionhack.entity.detailtour.ActivityModel

@Parcelize
data class TourModel(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val type: String = "Sights",
    val rating: Int = 3,
    val price: Int = 12000,
    val participants: String = "Tour price is for one person",
    val activitiesList: List<ActivityModel>
) : Parcelable