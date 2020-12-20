package ru.codeoverflow.junctionhack.ui.fragment.tours

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthScrollListener
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.fragment_tours.*
import kotlinx.android.synthetic.main.text_view_day_calendar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.ext.attachSnapHelperWithListener
import ru.codeoverflow.junctionhack.ext.hideKeyboard
import ru.codeoverflow.junctionhack.ext.showSnackbar
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.monthSelectedPosition
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursAdapterDelegate
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursMonthAdapterDelegate
import ru.codeoverflow.junctionhack.util.StartSnapHelper
import ru.codeoverflow.junctionhack.viewmodel.tours.ToursViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

private const val MAX_MONTH_COUNT = 11L

class ToursFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_tours

    private val viewModel by viewModel<ToursViewModel>()

    private val adapter: ListDelegationAdapter<List<TourModel>> by lazy {
        ListDelegationAdapter(
            toursAdapterDelegate {
                findNavController().navigate(
                    ToursFragmentDirections.actionToursFragmentToDetailTourFragment(
                        it
                    )
                )
            }
        )
    }

    private val today = LocalDate.now()

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

    private var onDayMonthClickListener: (CalendarDay) -> Unit = {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.isVisible = it
        })

        viewModel.listTours.observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        viewModel.startDay.observe(viewLifecycleOwner, Observer {
            etStartDate.setText(
                getString(
                    R.string.common_date,
                    it.day,
                    it.date.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                )
            )
            calendarView.notifyCalendarChanged()
            onDayMonthClickListener = {
                viewModel.endDay.postValue(it)
            }
        })

        viewModel.endDay.observe(viewLifecycleOwner, Observer {
            etEndDate.setText(
                getString(
                    R.string.common_date,
                    it.day,
                    it.date.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                )
            )
            calendarView.notifyCalendarChanged()
        })
        rvTours.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }

        etStartDate.setOnClickListener {
            hideKeyboard()
            groupCalendar.isVisible = true
            onDayMonthClickListener = {
                viewModel.startDay.postValue(it)
            }
        }

        etEndDate.setOnClickListener {
            hideKeyboard()
            groupCalendar.isVisible = true
            onDayMonthClickListener = {
                viewModel.endDay.postValue(it)
            }
        }

        val snapHelper = StartSnapHelper()
        rvMonth.attachSnapHelperWithListener(
            snapHelper = snapHelper,
            onSnapPositionChangeListener = { oldPos, newPos ->
                monthSelectedPosition = newPos
                monthAdapter.notifyItemChanged(oldPos)
                monthAdapter.notifyItemChanged(newPos)
                if (newPos >= 0) {
                    calendarView.smoothScrollToMonth(monthAdapter.items[newPos])
                }
            })
        rvMonth.adapter = monthAdapter

        val currentMonth = YearMonth.now()
        val lastMonth = currentMonth.plusMonths(MAX_MONTH_COUNT)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

        calendarView.setup(currentMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)
        calendarView.monthScrollListener = object : MonthScrollListener {
            override fun invoke(month: CalendarMonth) {
                if (monthAdapter.items[monthSelectedPosition].isBefore(month.yearMonth)) {
                    monthSelectedPosition += 1
                } else if (monthAdapter.items[monthSelectedPosition].isAfter(month.yearMonth)) {
                    monthSelectedPosition -= 1
                }
                monthAdapter.notifyDataSetChanged()
            }
        }

        calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                calendarBind(container, day)
            }
        }

        rvTours.adapter = adapter
    }

    private fun calendarBind(container: DayViewContainer, day: CalendarDay) {
        container.day = day

        val textView = container.textView
        textView.text = day.date.dayOfMonth.toString()

        btnChange.setOnClickListener {
            groupCalendar.isVisible = false
            showSnackbar(getString(R.string.snackbar_work_in_progress), parentFragment?.view)
        }

        if (day.date.isBefore(today)) {
            textView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black_50
                )
            )
            textView.setBackgroundResource(0)
        } else {
            container.isClickable = true
            if (viewModel.startDay.value != null && day.date.isBefore(viewModel.startDay.value?.date)) {
                container.isClickable = false
            }
            when {
                viewModel.startDay.value == null && viewModel.endDay.value == null ->
                    textView.setBackgroundResource(0)
                day == viewModel.startDay.value && (viewModel.endDay.value == null || day == viewModel.endDay.value) ->
                    textView.setBackgroundResource(R.drawable.bg_day)
                day == viewModel.startDay.value ->
                    textView.setBackgroundResource(R.drawable.bg_day_start)
                viewModel.endDay.value != null && day.date.isAfter(viewModel.startDay.value?.date) && day.date.isBefore(
                    viewModel.endDay.value?.date
                ) ->
                    textView.setBackgroundResource(R.drawable.bg_day_middle)
                day == viewModel.endDay.value -> textView.setBackgroundResource(R.drawable.bg_day_end)
                else -> textView.setBackgroundResource(0)
            }
        }
    }

    inner class DayViewContainer(view: View) :
        ViewContainer(view) {
        val textView = view.calendarDayText
        var day: CalendarDay? = null
        var isClickable = false

        init {
            view.setOnClickListener {
                if (day != null && isClickable) {
                    day?.let {
                        onDayMonthClickListener.invoke(it)
                    }
                }
            }
        }
    }
}
