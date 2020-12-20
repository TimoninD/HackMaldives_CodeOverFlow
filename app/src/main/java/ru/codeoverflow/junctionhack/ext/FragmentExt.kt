package ru.codeoverflow.junctionhack.ext

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.codeoverflow.junctionhack.R

fun Fragment.hideKeyboard() {
    val imm: InputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = activity?.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showSnackbar(message: String?, anchorView: View? = view) {
    anchorView?.let {
        val snackBar = Snackbar
            .make(it, message.orEmpty(), Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.main_color
            )
        )
        snackBar.show()
    }
}