package ru.codeoverflow.junctionhack.ui.fragment.detailtour

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
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

    private val args by navArgs<DetailTourFragmentArgs>()

    private val tourModel by lazy {
        args.tourModel
    }

    private val adapter: ListDelegationAdapter<List<ActivityModel>> by lazy {
        ListDelegationAdapter(
            detailTourAdapterDelegate()
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivClose.setOnClickListener {
            findNavController().popBackStack()
        }

        rvTourStep.adapter = adapter
        adapter.items = tourModel.activitiesList
        tourActivitiesSize = adapter.items.size
        adapter.notifyDataSetChanged()

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

        Glide.with(requireContext())
            .load(tourModel.image)
            .into(ivIcon)

        tvTitle.text = tourModel.title
        tvType.text = tourModel.type

        ratingBar.rating = tourModel.rating.toFloat()

        tvDescription.text = tourModel.description
        tvParticipants.text = tourModel.participants
    }
}