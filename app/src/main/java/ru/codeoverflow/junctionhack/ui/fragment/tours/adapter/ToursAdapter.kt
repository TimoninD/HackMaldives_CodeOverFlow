package ru.codeoverflow.junctionhack.ui.fragment.tours.adapter

import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_tour_month_item.view.*
import kotlinx.android.synthetic.main.rv_tours_item.view.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

fun toursAdapterDelegate() = adapterDelegate<TourModel, TourModel>(R.layout.rv_tours_item) {

    bind {
        with(itemView) {
            ivIcon.setImageResource(R.drawable.start_image)
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvType.text = item.type
            ratingBar.rating = 3f
        }
    }
}

var monthSelectedPosition = 0

fun toursMonthAdapterDelegate(onClick: (item: YearMonth, position: Int) -> Unit) =
    adapterDelegate<YearMonth, YearMonth>(R.layout.rv_tour_month_item) {

        bind {
            with(itemView) {
                setOnClickListener {
                    monthSelectedPosition = absoluteAdapterPosition
                    onClick.invoke(item, absoluteAdapterPosition)
                }
                tvMonth.text = item.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
                if (monthSelectedPosition == absoluteAdapterPosition) {
                    tvMonth.setTextColor(ContextCompat.getColor(context, R.color.main_color))
                    tvMonth.typeface = ResourcesCompat.getFont(context, R.font.monsterrat_bold)
                } else {
                    tvMonth.setTextColor(ContextCompat.getColor(context, R.color.gray_disabled))
                    tvMonth.typeface = ResourcesCompat.getFont(context, R.font.monsterrat_regular)
                }
            }
        }
    }