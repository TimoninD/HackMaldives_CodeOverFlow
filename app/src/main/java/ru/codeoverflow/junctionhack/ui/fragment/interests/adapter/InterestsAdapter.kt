package ru.codeoverflow.junctionhack.ui.fragment.interests.adapter

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_interests_item.view.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.interests.Interest

val interestSelectedList: MutableList<String> = mutableListOf()
fun interestsAdapterDelegate(onClick: (String) -> Unit) =
    adapterDelegate<Interest, Interest>(R.layout.rv_interests_item) {

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