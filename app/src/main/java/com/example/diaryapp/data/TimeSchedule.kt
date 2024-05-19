package com.example.diaryapp.data

import com.example.diaryapp.screen.navigation.Screen

sealed class TimeSchedule (
    val title: String,
    val rangeOfMonth: Int,
    val rangeOfYear: Int,
) {
    object OneMonth: TimeSchedule("1 month", 1, 0 )
    object SixMonths: TimeSchedule("6 months", 6, 0 )
    object OneYear: TimeSchedule("1 years", 0, 1 )

}

/* XỬ LÝ TÁC VỤ NGÀY THÁNG
    val date1 = Date() // Ngày hôm nay
    val calendar = Calendar.getInstance()
    calendar.time = date1
    calendar.add(Calendar.YEAR, 1) // Thêm 1 năm
    val date2 = calendar.time // Ngày 1 năm sau
 */
