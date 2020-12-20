package ru.codeoverflow.junctionhack.ui.fragment.detailtour.adapter

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_detail_tour_item.view.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.detailtour.ActivityModel

var tourActivitiesSize = 0
fun detailTourAdapterDelegate() =
    adapterDelegate<ActivityModel, ActivityModel>(R.layout.rv_detail_tour_item) {

        bind {
            with(itemView) {
                Glide.with(context)
                    .load(item.images?.firstOrNull() ?: R.drawable.start_image)
                    .circleCrop()
                    .into(ivIcon)

                tvTitle.text = item.title
                tvDescription.text = item.description
                tvDate.text = if ((item.date
                        ?: "").isNotBlank()
                ) item.date else "2${absoluteAdapterPosition} Dec 2020"

                if (absoluteAdapterPosition == 0) {
                    viewTopLine.isVisible = false
                }
            }
        }
    }