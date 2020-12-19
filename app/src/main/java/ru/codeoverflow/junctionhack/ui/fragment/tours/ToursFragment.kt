package ru.codeoverflow.junctionhack.ui.fragment.tours

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import kotlinx.android.synthetic.main.fragment_tours.*
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.ext.attachSnapHelperWithListener
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.monthSelectedPosition
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursAdapterDelegate
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursMonthAdapterDelegate
import ru.codeoverflow.junctionhack.util.DayViewContainer
import ru.codeoverflow.junctionhack.util.StartSnapHelper
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

private const val MAX_MONTH_COUNT = 11L

class ToursFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_tours

    private val adapter: ListDelegationAdapter<List<TourModel>> by lazy {
        ListDelegationAdapter(
            toursAdapterDelegate()
        ).apply {
            items = listOf(
                TourModel("", "1", "dbgdbfgdfbgndfgnmfdbgdfgnfgndfg"),
                TourModel("", "2", "dbgdbfgd2fbgndfgnmfdbgdfgnfgndfg"),
                TourModel("", "3", "dbgdbfgdf3bgndfgnmfdbgdfgnfgndfg")
            )
        }
    }

    private val monthList: List<YearMonth> by lazy {
        val list = mutableListOf<YearMonth>()

        var month = YearMonth.now()

        for (i in 0 until MAX_MONTH_COUNT) {
            list.add(month)
            month = month.plusMonths(1)
        }
        list
    }
    private val monthAdapter: ListDelegationAdapter<List<YearMonth>> by lazy {
        ListDelegationAdapter(
            toursMonthAdapterDelegate { item, position ->
                rvMonth.scrollToPosition(position)
                calendarView.smoothScrollToMonth(item)
                monthAdapter.notifyDataSetChanged()
            }
        ).apply {
            items = monthList
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTours.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }

        val snapHelper = StartSnapHelper()
        rvMonth.attachSnapHelperWithListener(
            snapHelper = snapHelper,
            onSnapPositionChangeListener = { oldPos, newPos ->
                monthSelectedPosition = newPos
                monthAdapter.notifyItemChanged(oldPos)
                monthAdapter.notifyItemChanged(newPos)
                if(newPos >= 0) {
                    calendarView.smoothScrollToMonth(monthAdapter.items[newPos])
                }
            })
        rvMonth.adapter = monthAdapter

        val currentMonth = YearMonth.now()
        val lastMonth = currentMonth.plusMonths(MAX_MONTH_COUNT)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

        calendarView.setup(currentMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)

        calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()
            }
        }

        rvTours.adapter = adapter
    }
}
