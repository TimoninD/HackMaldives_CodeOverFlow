package ru.codeoverflow.junctionhack.ext

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

fun View.updatePaddingTopOnApplySystemWindowInsets(
    offset: Int = 0,
    vararg applyTo: View? = arrayOf(this)
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        applyTo.forEach { v ->
            v?.updatePadding(
                top = insets.systemWindowInsetTop + offset
            )
        }
        insets
    }
}

fun View.updateMarginBottomOnApplySystemWindowInsets(
    offset: Int = 0,
    vararg applyTo: View? = arrayOf(this)
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        applyTo.forEach { v ->
            v?.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.systemWindowInsetBottom + offset
            }
        }
        insets
    }
}