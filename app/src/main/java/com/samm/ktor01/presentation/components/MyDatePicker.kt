package com.samm.ktor01.presentation.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.samm.ktor01.presentation.AstroViewModel
import java.util.*

@Composable
fun MyDatePicker(viewModel: AstroViewModel) {

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            mDate.value = "$dayOfMonth/${month + 1}/$year"

            viewModel.getData("$year-${month + 1}-$dayOfMonth")

        }, mYear, mMonth, mDay
    )

    Button(onClick = {
        mDatePickerDialog.show()
    }) {
        Text(text = "Open Date Picker", color = Color.White)
    }
}