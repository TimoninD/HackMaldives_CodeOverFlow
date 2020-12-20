package ru.codeoverflow.junctionhack.ui.fragment.detailtour

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_tour_detail.*
import org.koin.android.ext.android.inject
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.detailtour.ActivityModel
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.ext.showSnackbar
import ru.codeoverflow.junctionhack.model.storage.Cache
import ru.codeoverflow.junctionhack.ui.fragment.detailtour.adapter.detailTourAdapterDelegate
import ru.codeoverflow.junctionhack.ui.fragment.detailtour.adapter.tourActivitiesSize

class DetailTourFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_tour_detail

    private val cache: Cache by inject()

    private val adapter: ListDelegationAdapter<List<ActivityModel>> by lazy {
        ListDelegationAdapter(
            detailTourAdapterDelegate()
        ).apply {
            val list = listOf(
                ActivityModel(
                    title = "AMAYA RESORT & SPA KUDA RAH",
                    description = "Transfer: 25 minutes by seaplane + 10 minutes by boat or 25 minutes by domestic plane to Maamigili local airport + 20 minutes by boat.",
                    date = "20 December 2020"
                ),
                ActivityModel(
                    title = "AMAYA RESORT & SPA KUDA RAH",
                    description = "Transfer: 25 minutes by seaplane + 10 minutes by boat or 25 minutes by domestic plane to Maamigili local airport + 20 minutes by boat.",
                    date = "20 December 2020"
                ),
                ActivityModel(
                    title = "AMAYA RESORT & SPA KUDA RAH",
                    description = "Transfer: 25 minutes by seaplane + 10 minutes by boat or 25 minutes by domestic plane to Maamigili local airport + 20 minutes by boat.",
                    date = "20 December 2020"
                ),
                ActivityModel(
                    title = "AMAYA RESORT & SPA KUDA RAH",
                    description = "Transfer: 25 minutes by seaplane + 10 minutes by boat or 25 minutes by domestic plane to Maamigili local airport + 20 minutes by boat.",
                    date = "20 December 2020"
                ),
                ActivityModel(
                    title = "AMAYA RESORT & SPA KUDA RAH",
                    description = "Transfer: 25 minutes by seaplane + 10 minutes by boat or 25 minutes by domestic plane to Maamigili local airport + 20 minutes by boat.",
                    date = "20 December 2020"
                )
            )
            tourActivitiesSize = list.size
            items = list
        }
    }

    private val tourModel = TourModel(
        "1",
        "",
        "Maldives Beach Tour",
        "An ideal place for a secluded holiday - each resort is located on a separate island, you will not find crowds of tourists here. Your number will always be \"on the first line\", and the clear waters of the Indian Ocean are just a stone's throw away. Fine sand, coconut palms, atoll lagoons, where there are no hidden currents, and strange fish swim under the water, they are eagerly awaiting tourists."
        ,
        "Sights",
        4
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTourStep.adapter = adapter

        ivClose.setOnClickListener {
            findNavController().popBackStack()
        }

        btnFavorite.setOnClickListener {
            val favoriteTour = cache.favoriteTours ?: mutableListOf()
            if (!favoriteTour.contains(tourModel)) {
                favoriteTour.add(tourModel)
                cache.favoriteTours = favoriteTour
            }
        }

        btnBuy.setOnClickListener {
            showSnackbar(getString(R.string.snackbar_work_in_progress))
        }

        tvTitle.text = tourModel.title
        tvType.text = tourModel.type
        ratingBar.rating = tourModel.rating.toFloat()
        tvDescription.text = tourModel.description
        tvParticipants.text = "Tour price is for one person"
    }
}