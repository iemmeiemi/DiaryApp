package com.example.diaryapp.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Background2
import com.example.diaryapp.theme.Black
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarComponent(
    selections: SnapshotStateList<CalendarDay>,
    type: MutableState<String>,
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val dayOfWeek = remember { DayOfWeek.MONDAY } //daysOfWeek()
    val daysOfWeek = listOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY )
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = dayOfWeek
    )

//    val selections = remember { mutableStateListOf<CalendarDay>( CalendarDay(position = DayPosition.MonthDate, date = LocalDate.now() ) ) }
//    val type = remember { mutableStateOf<String>("month") }

    Column {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
//            Button(
//                colors = ButtonColors(
//                    containerColor = if (type.value == "day") Color.DarkGray else Color.LightGray,
//                    contentColor = if (type.value == "day") Color.White else Color.Black,
//                    disabledContentColor = Black ,
//                    disabledContainerColor = Background,
//                ),
//                modifier = Modifier.width(100.dp),
//                onClick = { type.value = "day" },
//            ) {
//                Text(text = "Day")
//            }
//
//            Spacer(modifier = Modifier.width(20.dp))
//            Button(
//                colors = ButtonColors(
//                    containerColor = if (type.value == "month") Background2 else Background,
//                    contentColor = if (type.value == "month") Color.White else Color.Black,
//                    disabledContentColor = Black ,
//                    disabledContainerColor = Background,
//                ),
//                modifier = Modifier.width(100.dp),
//                onClick = { type.value = "month" },
//            ) {
//                Text(text = "Month")
//            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(day, isSelected = selections.contains(day)) { clicked ->
                    if (selections.contains(clicked)) {
                        selections.remove(clicked)
                    } else {
                        selections.clear()
                        selections.add(clicked)
                    }
                }
            },
            monthHeader = {
                    month ->
                MonthHeader(daysOfWeek = daysOfWeek, month)
            },
        )
    }
}

@Composable
private fun MonthHeader(daysOfWeek: List<DayOfWeek>, month: CalendarMonth) {
    Column{
        Text(
            text = YearMonth.parse(month.yearMonth.toString()).toString(),
            modifier = Modifier
                .background(Color.Gray, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MonthHeader"),
        ) {

            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .background(Background)
                        .padding(0.dp, 15.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    text = dayOfWeek.toString().substring(0, 3),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .testTag("MonthDay")
            .padding(6.dp)
            .clip(CircleShape)
            .background(color = if (isSelected) Color.Black else Color.Transparent)
            // Disable clicks on inDates/outDates
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                //showRipple = !isSelected,
                onClick = { onClick(day) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val textColor = when (day.position) {
            // Color.Unspecified will use the default text color from the current theme
            DayPosition.MonthDate -> if (isSelected) Color.White else Color.Unspecified
            DayPosition.InDate, DayPosition.OutDate -> Color.Gray
        }
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
fun PreviewCalendar() {
    val selections = remember { mutableStateListOf( CalendarDay(position = DayPosition.MonthDate, date = LocalDate.now() ) ) }
    val type = remember { mutableStateOf("month") }
    CalendarComponent(
        selections, type
    )
}