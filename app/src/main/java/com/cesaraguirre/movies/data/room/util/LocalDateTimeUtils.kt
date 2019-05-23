package com.cesaraguirre.movies.data.room.util

import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.time.temporal.TemporalQueries.localDate



object LocalDateTimeUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd"
    private const val TIME_FORMAT = "HHmm"
    private const val HUMAN_REDABLE_FORMAT = "d MMMM yyyy"

    @JvmStatic
    fun convertStringToLocalDate(date: String): LocalDate {
        return LocalDate.parse(date, DateTimeFormat.forPattern(DATE_FORMAT))
    }

    @JvmStatic
    fun convertLocalDateToString(date: LocalDate): String {
        val fmt = DateTimeFormat.forPattern(DATE_FORMAT)
        return fmt.print(date)
    }

    @JvmStatic
    fun convertLocalDateToHumanRedable(date: LocalDate): String {
        val formatter = DateTimeFormat.forPattern(HUMAN_REDABLE_FORMAT)
        return date.toString(formatter)
    }

    @JvmStatic
    fun convertStringToLocalTime(time: String): LocalTime {
        return LocalTime.parse(time, DateTimeFormat.forPattern(TIME_FORMAT))
    }

    @JvmStatic
    fun convertLocalTimeToString(time: LocalTime): String {
        val fmt = DateTimeFormat.forPattern(TIME_FORMAT)
        return fmt.print(time)
    }

}