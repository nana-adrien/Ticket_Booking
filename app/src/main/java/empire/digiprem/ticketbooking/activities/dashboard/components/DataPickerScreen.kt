package empire.digiprem.ticketbooking.activities.dashboard.components

import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.ticketbooking.R
import java.text.SimpleDateFormat
import java.util.Locale


fun showDataPickerDialog(
        context : Context ,
        calendar : Calendar ,
        dataFormat : SimpleDateFormat ,
        onDateSelected : (String) -> Unit ,
) {
   val year = calendar.get(Calendar.YEAR)
   val month = calendar.get(Calendar.MONTH)
   val day = calendar.get(Calendar.DAY_OF_MONTH)
   android.app.DatePickerDialog(
      context ,
      { _ , selectedYear , selectedMonth , selectedDay ->
         calendar.set(selectedYear , selectedMonth , selectedDay)
         val formattedDate = dataFormat.format(calendar.time)
         onDateSelected(formattedDate)
      } , year , month , day
   ).show()
}

@Composable
fun DatePickerScreen(modifier : Modifier = Modifier) {
   val context = LocalContext.current
   val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault()) }
   var departureCalendar = remember { Calendar.getInstance() }
   val returnCalendar = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR , 1) } }

   var departureDate by remember { mutableStateOf(dateFormat.format(departureCalendar.time)) }
   var returnDate by remember { mutableStateOf(dateFormat.format(departureCalendar.time)) }

   Row() {
      DatePickerItem(
         modifier = modifier ,
         dateText = departureDate ,
         onDateSelected = { selectedDate ->
            departureDate = selectedDate
         } ,
         dateFormat = dateFormat ,
         context = context ,
         calendar = departureCalendar ,
      )
      Spacer(modifier=Modifier.width(16.dp))
      DatePickerItem(
         modifier = modifier ,
         dateText = returnDate ,
         onDateSelected = { selectedDate ->
            returnDate = selectedDate
         } ,
         dateFormat = dateFormat ,
         context = context ,
         calendar = returnCalendar ,
      )
   }
}

@Composable
fun DatePickerItem(
        modifier : Modifier = Modifier ,
        dateText : String ,
        onDateSelected : (String) -> Unit ,
        dateFormat : SimpleDateFormat ,
        calendar : Calendar ,
        context : Context ,
) {

   Row(
      modifier = Modifier
         .height(60.dp)
         .padding(top = 8.dp)
         .background(color = colorResource(R.color.lightPurPle) , shape = RoundedCornerShape(8.dp))
         .clickable {
            showDataPickerDialog(context , calendar , dateFormat , onDateSelected)
         } ,
      verticalAlignment = Alignment.CenterVertically
   ) {
      Image(
         painter = painterResource(R.drawable.calendar_ic) ,
         contentDescription = null ,
         modifier = Modifier
            .padding(start = 8.dp)
            .size(24.dp)
      )
      Text(
         text = dateText ,
         color = Color.Black ,
         modifier = Modifier.padding(horizontal = 8.dp) ,
         fontWeight = FontWeight.Bold ,
         fontSize = 13.sp ,
         textAlign = TextAlign.Center

      )

   }

}



