package ru.codeoverflow.junctionhack.entity

import java.util.*

const val DEFAULT_LOCALE_LANGUAGE = "ru"

const val DEFAULT_LOCALE_COUNTRY = "RU"

const val DECIMAL_SEPARATOR = ','

const val GROUPING_DELIMITER = ' '

inline val APP_LOCALE get() = Locale(DEFAULT_LOCALE_LANGUAGE, DEFAULT_LOCALE_COUNTRY)

