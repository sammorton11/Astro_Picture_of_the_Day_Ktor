package com.samm.ktor01.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun MyDatePicker(getData: (String) -> Unit)  {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()
    val maxDateInMillis = calendar.timeInMillis


    val selectedDate = remember { mutableStateOf("") }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                selectedDate.value = "$year-${month + 1}-$dayOfMonth"
            },
            year, month, dayOfMonth
        ).apply {
            datePicker.maxDate = maxDateInMillis
            setOnDismissListener {
                if (selectedDate.value.isNotBlank()) {
                    getData(selectedDate.value)
                }
            }
        }
    }

    // Button to open date picker
    Button(
        onClick = {
            datePickerDialog.show()
        },
        modifier = Modifier.padding(bottom = 15.dp)
    ) {
        Text(text = "Open Date Picker", color = Color.White)
    }
}