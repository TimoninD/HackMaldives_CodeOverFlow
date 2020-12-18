package ru.codeoverflow.junctionhack.ext

import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

/**
 * добавление маски к EditText с помощью библиотеки RedMadRobot
 *
 * @param mask - паттерн маски
 * @param changeText - лямбда которая вызывается при изменение текста
 */
fun EditText.setMask(
    mask: String,
    changeText: (
        maskFilled: Boolean,
        extractedValue: String,
        formattedValue: String) -> Unit = { _, _, _ -> }
) {
    val affineFormats: MutableList<String> = ArrayList()
    affineFormats.add(mask)

    val listener = MaskedTextChangedListener.installOn(
        this,
        mask,
        affineFormats, AffinityCalculationStrategy.PREFIX,
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(
                maskFilled: Boolean,
                extractedValue: String,
                formattedValue: String
            ) {
                changeText(maskFilled, extractedValue, formattedValue)
            }
        }
    )
}