package ru.codeoverflow.junctionhack.ext

import ru.codeoverflow.junctionhack.entity.APP_LOCALE
import ru.codeoverflow.junctionhack.entity.DECIMAL_SEPARATOR
import ru.codeoverflow.junctionhack.entity.GROUPING_DELIMITER
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Number.format(): String {
    val price = BigDecimal(toString()).abs()
    val pattern =
        if (price.setScale(0, RoundingMode.FLOOR) == price
            || price.setScale(0, RoundingMode.FLOOR) - price == BigDecimal(0.0.toString())
        ) {
            "###,###"
        } else {
            "###,###.00"
        }
    val formatSymbols = DecimalFormatSymbols(APP_LOCALE).apply {
        decimalSeparator = DECIMAL_SEPARATOR
        groupingSeparator = GROUPING_DELIMITER
    }
    return DecimalFormat(pattern, formatSymbols)
        .apply {
            roundingMode = RoundingMode.FLOOR
        }
        .format(price)
}