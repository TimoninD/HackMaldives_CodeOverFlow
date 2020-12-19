package ru.codeoverflow.junctionhack.ui.fragment.tours.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_tours_item.view.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel

fun toursAdapterDelegate() = adapterDelegate<TourModel, TourModel>(R.layout.rv_tours_item) {

    bind {
        with(itemView) {
            ivIcon.setImageResource(R.drawable.start_image)
            tvTitle.text = item.title
            tvDescription.text = item.description
        }
    }
}