package ru.codeoverflow.junctionhack.ext

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import ru.codeoverflow.junctionhack.util.SnapOnScrollListener

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
    onSnapPositionChangeListener: (oldPosition: Int, newPosition: Int) -> Unit
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(
        snapHelper,
        behavior
    ) { oldPosition, newPosition ->
        onSnapPositionChangeListener.invoke(
            oldPosition,
            newPosition
        )
    }
    addOnScrollListener(snapOnScrollListener)
}