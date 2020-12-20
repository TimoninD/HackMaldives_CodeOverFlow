package ru.codeoverflow.junctionhack.ui.fragment.interests

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_interests.*
import org.koin.android.ext.android.inject
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.interests.Interest
import ru.codeoverflow.junctionhack.ext.showSnackbar
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.model.storage.Cache
import ru.codeoverflow.junctionhack.ui.fragment.interests.adapter.interestSelectedList
import ru.codeoverflow.junctionhack.ui.fragment.interests.adapter.interestsAdapterDelegate

private const val SPAN_COUNT = 3

class InterestsFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_interests

    private val adapter: ListDelegationAdapter<List<Interest>> by lazy {
        ListDelegationAdapter(
            interestsAdapterDelegate {
                if (interestSelectedList.contains(it)) {
                    interestSelectedList.remove(it)
                } else {
                    interestSelectedList.add(it)
                }
                adapter.notifyDataSetChanged()
            }
        ).apply {
            items = listInterests
        }
    }

    private val cache: Cache by inject()

    private val prefs: Prefs by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            //prefs.isTestShow = true
            if (interestSelectedList.isNotEmpty()) {
                cache.interests = interestSelectedList
                findNavController().navigate(InterestsFragmentDirections.actionInterestsFragmentToBudgetFragment())
            } else {
                showSnackbar(getString(R.string.snackbar_select_interests))
            }
        }

        rvInterests.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        rvInterests.adapter = adapter
    }

    private val listInterests: List<Interest> by lazy {
        listOf(
            Interest(imageId = R.drawable.diving, name = "Diving"),
            Interest(imageId = R.drawable.rafting, name = "Rafting"),
            Interest(imageId = R.drawable.surfing, name = "Surfing"),
            Interest(imageId = R.drawable.rock_climbing, name = "Rock climbing"),
            Interest(imageId = R.drawable.snowboarding, name = "Snowboarding"),
            Interest(imageId = R.drawable.one_day_tours, name = "One day tours"),
            Interest(imageId = R.drawable.multi_day_tour, name = "Multi-day tour"),
            Interest(imageId = R.drawable.religious_tours, name = "Religious tours"),
            Interest(imageId = R.drawable.thematic_tours, name = "Thematic tours"),
            Interest(imageId = R.drawable.event_tours, name = "Event tours"),
            Interest(imageId = R.drawable.individual_program, name = "Individual program"),
            Interest(imageId = R.drawable.group_tour, name = "Group tour"),
            Interest(imageId = R.drawable.for_youth, name = "For youth"),
            Interest(imageId = R.drawable.wedding_tour, name = "Wedding tour"),
            Interest(imageId = R.drawable.family_tours, name = "Family tours"),
            Interest(imageId = R.drawable.school_trips, name = "School trips"),
            Interest(imageId = R.drawable.for_the_elderly, name = "For the elderly")
        )
    }

}