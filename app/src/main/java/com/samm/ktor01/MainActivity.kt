package com.samm.ktor01

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.samm.ktor01.presentation.AstroViewModel
import com.samm.ktor01.presentation.components.BottomNavigation
import com.samm.ktor01.presentation.navigation.AppNavigation
import com.samm.ktor01.ui.theme.Ktor01Theme
import java.util.*

/**
 *      Todo:
 *          - Conditional for media types - image, and video
 *          - Clean up
 *          - Component alignments
 */

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: AstroViewModel = viewModel()
            viewModel.getSingleItemData()
            viewModel.getDataListByCount(10)

            Ktor01Theme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigation(onTabSelected = {
                            // Navigate to the selected screen using a navController
                            when (it) {
                                0 -> { navController.navigate("screen1") }
                                1 -> { navController.navigate("screen2") }
                                2 -> { navController.navigate("screen3") }
                            }
                        })
                    }, content = {
                        AppNavigation(navController)
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetSingleItemData(viewModel: AstroViewModel) {

    val data = viewModel.responseFlow.collectAsStateWithLifecycle()
    val date = data.value?.date
    val explanation = data.value?.explanation
    val hdurl = data.value?.hdUrl
    val title = data.value?.title
    val media_type = data.value?.mediaType // Todo: use this to determine if we need a media player or async image


    LazyColumn {

        data.value?.let {
            item {
                Card(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        title?.let { Text(text = it) }
                        hdurl?.let { AsyncImage(model = it, contentDescription = "HD Image") }
                        explanation?.let { Text(text = it) }
                        date?.let { Text(text = it) }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAstroDataByDate(viewModel: AstroViewModel) {

    val data = viewModel.responseByFlowList.collectAsStateWithLifecycle()

    val date = data.value?.date
    val explanation = data.value?.explanation
    val hdurl = data.value?.hdUrl
    val title = data.value?.title
    val media_type = data.value?.mediaType // Todo: use this to determine if we need a media player or async image


    LazyColumn {

        data.value?.let {
            item {
                Card(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        title?.let { Text(text = it) }
                        hdurl?.let { AsyncImage(model = it, contentDescription = "HD Image") }
                        explanation?.let { Text(text = it) }
                        date?.let { Text(text = it) }
                    }
                }
            }
        }

        item {
            MyDatePicker(viewModel = viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAstroDataList(
    viewModel: AstroViewModel,
    count: Int = 10
) {
    val dataList = viewModel.responseFlowList.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
    ) {
        dataList.value.size.let { amountOfItems ->

            items(amountOfItems) { index ->
                
                val copyright = dataList.value[index]?.copyright
                val date = dataList.value[index]?.date
                val explanation = dataList.value[index]?.explanation
                val hdurl = dataList.value[index]?.hdUrl
                val title = dataList.value[index]?.title

                Card(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        title?.let { Text(text = it) }
                        hdurl?.let { AsyncImage(model = it, contentDescription = "HD Image") }
                        explanation?.let { Text(text = it) }
                        date?.let { Text(text = it) }
                        copyright?.let { Text(text = it) }
                    }
                }
            }
        }
    }
}

@Composable
fun MyDatePicker(viewModel: AstroViewModel) {
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            mDate.value = "$dayOfMonth/${month+1}/$year"

            viewModel.getData("$year-0${month+1}-$dayOfMonth")

        }, mYear, mMonth, mDay
    )

    Button(onClick = {
        mDatePickerDialog.show()
    }, colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF0F9D58)) ) {
        Text(text = "Open Date Picker", color = Color.White)
    }
}

@Preview
@Composable
fun DatePreview() {
    MyContent()
}

@Composable
fun MyContent() {

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
        { _: DatePicker, year: Int, month: Int, day: Int -> mDate.value = "$day/${month+1}/$year" },
        mYear,
        mMonth,
        mDay
    )

    Button(onClick = {
        mDatePickerDialog.show()
    }, colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF0F9D58)) ) {
        Text(text = "Open Date Picker", color = Color.White)
    }




    LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            // Creating a button that on
            // click displays/shows the DatePickerDialog
            Button(onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF0F9D58)) ) {
                Text(text = "Open Date Picker", color = Color.White)
            }

            // Adding a space of 100dp height
            Spacer(modifier = Modifier.size(100.dp))

            // Displaying the mDate value in the Text
            Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
        }

    }
}