package ru.codeoverflow.junctionhack.ui.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.ui.fragment.tours.ToursFragmentDirections
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursAdapterDelegate

class ProfileFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_profile


    private val adapter: ListDelegationAdapter<List<TourModel>> by lazy {
        ListDelegationAdapter(
            toursAdapterDelegate {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToDetailTourFragment(
                        it.id
                    )
                )
            }
        ).apply {
            items = listOf(
                TourModel("1", "", "1", "dbgdbfgdfbgndfgnmfdbgdfgnfgndfg"),
                TourModel("2", "", "2", "dbgdbfgd2fbgndfgnmfdbgdfgnfgndfg"),
                TourModel("3", "", "3", "dbgdbfgdf3bgndfgnmfdbgdfgnfgndfg")
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(R.drawable.start_image)
            .transform(RoundedCorners(resources.getDimension(R.dimen.radius_18).toInt()))
            .into(ivAvatar)

        tvName.text = "Ekaterina Nosova"
        tvGeo.text = "Moscow, Russia"

        rvTours.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }
        rvTours.adapter = adapter
    }
}