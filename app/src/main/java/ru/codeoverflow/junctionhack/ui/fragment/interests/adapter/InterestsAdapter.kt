package ru.codeoverflow.junctionhack.ui.fragment.interests.adapter

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_interests_item.view.*
import kotlinx.android.synthetic.main.rv_interests_item.view.tvName
import kotlinx.android.synthetic.main.rv_interests_item.view.viewBackground
import kotlinx.android.synthetic.main.rv_interests_more_item.*
import kotlinx.android.synthetic.main.rv_interests_more_item.view.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.interests.BaseInterest
import ru.codeoverflow.junctionhack.entity.interests.Interest
import ru.codeoverflow.junctionhack.entity.interests.InterestMore

val interestSelectedList: MutableList<String> = mutableListOf()
fun interestsAdapterDelegate(onClick: (String) -> Unit) =
    adapterDelegate<Interest, BaseInterest>(R.layout.rv_interests_item) {

        bind {
            with(itemView) {
                setOnClickListener {
                    onClick.invoke(item.name)
                }
                Glide.with(context)
                    .load(item.imageId)
                    .transform(RoundedCorners(resources.getDimension(R.dimen.radius_10).toInt()))
                    .into(ivItem)
                tvName.text = item.name
                viewBackground.isVisible = interestSelectedList.contains(item.name)
            }
        }
    }

fun interestsMoreAdapterDelegate(onClick: (String) -> Unit) =
    adapterDelegate<InterestMore, BaseInterest>(R.layout.rv_interests_more_item) {

        bind {
            with(itemView) {
                setOnClickListener {
                    onClick.invoke(item.name)
                }

                Glide.with(context)
                    .load(item.backgroundId)
                    .transform(RoundedCorners(resources.getDimension(R.dimen.radius_10).toInt()))
                    .into(viewItem)

                tvName.text = item.showName
                tvName.setTextColor(ContextCompat.getColor(context, item.textColorId))
                viewBackground.isVisible = interestSelectedList.contains(item.name)
            }
        }
    }