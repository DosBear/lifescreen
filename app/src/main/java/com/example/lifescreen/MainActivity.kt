package com.example.lifescreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    var weeks1 = 0;
    val maxWeek = 52;
    val maxYear = 60;

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val start: Date = dateFormat.parse("1983-10-18")
        val end: Date = dateFormat.parse("2022-01-21")
        weeks1 = option1(start, end)
        setContent {
            Column {
                TopAppBar {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Меню")
                    }
                    Text("Жизнь в неделях", fontSize = 22.sp)
                }

                Column {

                    for (yr in 0..maxYear) {
                        Row {
                            Box(modifier = Modifier.padding(1.dp).background(Color.Cyan)) {
                                Text(
                                    text = (yr).toString(),
                                    fontSize = 5.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            LazyVerticalGrid(
                                modifier = Modifier.padding(1.dp),
                                cells = GridCells.Fixed(maxWeek), // 2
                            ) {
                                items(count = maxWeek) { index ->
                                    DrawBox(yr, index)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
    @Composable
    fun DrawBox(yr: Int, offset: Int)
    {
        if(yr*52 + offset<=weeks1)
        {
            Box( modifier = Modifier.aspectRatio(1f) .padding(1.dp) .background(Color.DarkGray) )
        } else if(yr*52 + offset>weeks1 && yr < 55)
        {
            Box( modifier = Modifier.aspectRatio(1f) .padding(1.dp) .background(Color.Yellow) )

        } else if(yr>=55)
        {
            Box( modifier = Modifier.aspectRatio(1f) .padding(1.dp) .background(Color.Red) )
        }
    }


    fun option1(start: Date?, end: Date?): Int {
        val cal: Calendar = GregorianCalendar()
        cal.time = start
        var weeks = 0
        while (cal.time.before(end)) {
            cal.add(Calendar.WEEK_OF_YEAR, 1)
            weeks++
        }
        return weeks
    }



}