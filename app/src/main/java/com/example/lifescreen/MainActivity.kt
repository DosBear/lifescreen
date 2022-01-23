package com.example.lifescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    var weeks1 = 0;
    val maxWeek = 52;
    val maxYear = 60;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val start: Date = dateFormat.parse("1983-10-18")
        val end: Date = dateFormat.parse("2022-01-21")
        weeks1 = option1(start, end)

        setContent {

            Column {
                TopAppBar {
                    IconButton(onClick = {
                        

                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Меню")
                    }
                    Text("Жизнь в неделях", fontSize = 22.sp)
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(1) { rowCount ->
                        Row(
                            modifier = Modifier
                        ) {
                            for (wk in 0 until 5) {
                                Box(
                                    modifier = Modifier.padding(1.dp)
                                        .height(6.dp)
                                        .weight(1f)
                                        .background(Color.Cyan)
                                ){
                                    Text( modifier = Modifier.align(Alignment.CenterEnd),
                                        text = ((wk+1)*10).toString(),
                                        fontSize = 5.sp
                                    )
                                }
                            }
                        }
                    }
                }



                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(maxYear) { year ->
                        Row(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Box(modifier = Modifier.padding(1.dp).height(6.dp).weight(1.5f).background(Color.Cyan)) {
                                Text(
                                    text = (year).toString(),
                                    fontSize = 5.sp,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                            for (week in 0 until maxWeek) {
                                if(year*52 + week<=weeks1)
                                {
                                    Box( modifier = Modifier.padding(1.dp).height(6.dp).weight(1f) .background(Color.DarkGray) )
                                } else if(year*52 + week>weeks1 && year < 55)
                                {
                                    Box( modifier = Modifier.padding(1.dp) .height(6.dp).weight(1f) .background(Color.Yellow) )

                                } else
                                {
                                    Box( modifier = Modifier.padding(1.dp) .height(6.dp).weight(1f) .background(Color.Red) )
                                }
                            }
                        }
                    }
                }

            }
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
