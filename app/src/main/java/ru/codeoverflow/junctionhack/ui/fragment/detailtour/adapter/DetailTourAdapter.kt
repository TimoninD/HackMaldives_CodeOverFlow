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
                    .load(R.drawable.start_image)
                    .circleCrop()
                    .into(ivIcon)

                tvTitle.text = item.title
                tvDescription.text = item.description
                tvDate.text = item.date

                if (absoluteAdapterPosition == 0) {
                    viewTopLine.isVisible = false
                } else if (absoluteAdapterPosition == tourActivitiesSize - 1) {
                    viewBottomLine.isVisible = false
                    tvDate.isVisible = false
                }
            }
        }
    }