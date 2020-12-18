package ru.codeoverflow.junctionhack.ext

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val imm: InputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = activity?.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}