package empire.digiprem.ticketbooking.activities.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.ticketbooking.R

@Composable
fun PassengerCounter(
        title : String ,
        modifier : Modifier = Modifier ,
        onItemSelected : (String) -> Unit ,
) {
   var passengerCounter by remember { mutableStateOf(0) }
   Box(
      modifier = modifier
         .height(60.dp)
         .padding(top=8.dp)
         .background(
            color = colorResource(R.color.lightPurPle) ,
            shape = RoundedCornerShape(10.dp)
         ) , contentAlignment = Alignment.Center
   ) {
      Row(
         modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
         verticalAlignment = Alignment.CenterVertically
      ) {
         Image(
            painter = painterResource(R.drawable.passenger_ic) ,
            contentDescription = null ,
            modifier = Modifier.size(24.dp)
         )
         Spacer(modifier = Modifier.width(8.dp))

         // minus butonn
         Box(
            modifier = Modifier
               .weight(1f)
               .fillMaxHeight()
               .clickable {
                  if (passengerCounter > 0) {
                     passengerCounter--
                     onItemSelected(passengerCounter.toString())
                  }
               } ,
            contentAlignment = Alignment.Center) {

            Text(
               text = "-" ,
               color = Color.Black ,
               fontSize = 16.sp ,
               fontWeight = FontWeight.Bold
            )
         }

         // passenger count
         Text(
            text = "$passengerCounter $title" ,
            color = Color.Black ,
            fontWeight = FontWeight.Bold ,
            fontSize = 14.sp ,
            modifier = Modifier.padding(horizontal = 8.dp)
         )
         // plus butonn
         Box(
            modifier = Modifier
               .weight(1f)
               .fillMaxHeight()
               .clickable {
                  passengerCounter++
                  onItemSelected(passengerCounter.toString())
               } ,
            contentAlignment = Alignment.Center) {

            Text(
               text = "+" ,
               color = Color.Black ,
               fontSize = 16.sp ,
               fontWeight = FontWeight.Bold
            )
         }

      }

   }

}