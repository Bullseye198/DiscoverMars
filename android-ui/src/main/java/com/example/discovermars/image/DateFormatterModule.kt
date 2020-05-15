package com.example.discovermars.image

object DateFormatterModule {

    fun onDateFormatted(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        val month: Int = monthOfYear + 1
        var formattedMonth: String = "" + month
        var formattedDayOfMonth = "" + dayOfMonth

        if (month < 10) {
            formattedMonth = "0" + month
        }
        if (dayOfMonth < 10) {
            formattedDayOfMonth = "0" + dayOfMonth
        }
        return "$year-$formattedMonth-$formattedDayOfMonth"
    }
}