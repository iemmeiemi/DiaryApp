package com.example.diaryapp.screen.components

import androidx.compose.runtime.remember
import java.time.YearMonth
//
//@Composable
//fun MainScreen() {
//    val currentMonth = remember { YearMonth.now() }
//    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
//    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
//    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
//
//    val state = rememberCalendarState(
//        startMonth = startMonth,
//        endMonth = endMonth,
//        firstVisibleMonth = currentMonth,
//        firstDayOfWeek = firstDayOfWeek
//    )
//
//    HorizontalCalendar(
//        state = state,
//        dayContent = { Day(it) }
//    )
//
////    If you need a vertical calendar.
////    VerticalCalendar(
////        state = state,
////        dayContent = { Day(it) }
//    )
//}