package ru.codeoverflow.junctionhack.util

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import ru.codeoverflow.junctionhack.R

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
}
