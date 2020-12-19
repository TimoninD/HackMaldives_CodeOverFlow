package ru.codeoverflow.junctionhack.viewmodel.tours

import androidx.lifecycle.MutableLiveData
import com.kizitonwose.calendarview.model.CalendarDay
import ru.codeoverflow.junctionhack.viewmodel.BaseViewModel

class ToursViewModel : BaseViewModel() {

    val startDay: MutableLiveData<CalendarDay> = MutableLiveData()
    val endDay: MutableLiveData<CalendarDay> = MutableLiveData()
}