package ru.codeoverflow.junctionhack.ui.fragment.tours

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_tours.*
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursAdapterDelegate

class ToursFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_tours

    private val adapter: ListDelegationAdapter<List<TourModel>> by lazy {
        ListDelegationAdapter(
            toursAdapterDelegate()
        ).apply { items = listOf(
            TourModel("","1","dbgdbfgdfbgndfgnmfdbgdfgnfgndfg"),
            TourModel("","2","dbgdbfgd2fbgndfgnmfdbgdfgnfgndfg"),
            TourModel("","3","dbgdbfgdf3bgndfgnmfdbgdfgnfgndfg")
        ) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTours.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }

        rvTours.adapter = adapter
    }
}
