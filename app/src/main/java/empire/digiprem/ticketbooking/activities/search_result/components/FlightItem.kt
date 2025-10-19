package empire.digiprem.ticketbooking.activities.search_result.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import empire.digiprem.ticketbooking.R
import empire.digiprem.ticketbooking.activities.seat_select.SeatSelectActivity
import empire.digiprem.ticketbooking.domain.FlightModel

@Composable
fun FlightItem(flight : FlightModel , index : Int) {
   val context = LocalContext.current

   ConstraintLayout(
      modifier = Modifier
         .padding(horizontal = 16.dp , vertical = 8.dp)
         .fillMaxWidth()
         .clickable {
            val intent = Intent(context , SeatSelectActivity::class.java).apply {
               putExtra("flight" , flight)
            }
            startActivity(context , intent , null)
         }
         .border(
            color = colorResource(R.color.darkPurPle2) ,
            shape = RoundedCornerShape(15.dp) ,
            width = 1.dp
         )
         .background(
            color = colorResource(R.color.lightPurPle) ,
            shape = RoundedCornerShape(15.dp)
         )
   ) {
      val (
         logo , timeText , airplaneIcon , dashLine , priceText ,
         seatIcon , classText , fromText , fromShorText , toText , toShortTxt ,
      ) = createRefs()

      AsyncImage(
         model = flight.airlineLogo ,
         contentDescription = null ,
         modifier = Modifier
            .size(200.dp , 50.dp)
            .constrainAs(logo) {
               start.linkTo(parent.start)
               top.linkTo(parent.top)
               end.linkTo(parent.end)
            }
      )

      Text(
         text = flight.arriveTime ,
         textAlign = TextAlign.Center ,
         fontWeight = FontWeight.Bold ,
         fontSize = 12.sp ,
         color = colorResource(R.color.darkPurPle2) ,
         modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(timeText) {
               start.linkTo(parent.start)
               top.linkTo(logo.bottom)
               end.linkTo(parent.end)
            }
      )

      Image(
         painter = painterResource(R.drawable.line_airple_blue) ,
         contentDescription = null ,
         modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(airplaneIcon) {
               start.linkTo(parent.start)
               top.linkTo(timeText.bottom)
               end.linkTo(parent.end)
            }
      )
      Image(
         painter = painterResource(R.drawable.dash_line) ,
         contentDescription = null ,
         modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(dashLine) {
               start.linkTo(parent.start)
               top.linkTo(airplaneIcon.bottom)
               end.linkTo(parent.end)
            }
      )

      Text(
         text = "$${String.format("%.2f" , flight.price)}" ,
         textAlign = TextAlign.Center ,
         fontWeight = FontWeight.SemiBold ,
         fontSize = 25.sp ,
         color = colorResource(R.color.orange) ,
         modifier = Modifier
            .padding(8.dp)
            .constrainAs(priceText) {
               top.linkTo(dashLine.bottom)
               end.linkTo(parent.end)
            }
      )
      Image(
         painter = painterResource(R.drawable.seat_black_ic) ,
         contentDescription = null ,
         modifier = Modifier
            .padding(8.dp)
            .constrainAs(seatIcon) {
               start.linkTo(parent.start)
               top.linkTo(dashLine.bottom)
               bottom.linkTo(parent.bottom)
            }
      )
      Text(
         text = flight.classSeat ,
         textAlign = TextAlign.Center ,
         fontWeight = FontWeight.Bold ,
         fontSize = 12.sp ,
         color = colorResource(R.color.darkPurPle2) ,
         modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(classText) {
               start.linkTo(seatIcon.end)
               top.linkTo(seatIcon.top)
               bottom.linkTo(seatIcon.bottom)
            }
      )
      Text(
         text = flight.from ,
         fontSize = 14.sp ,
         color = Color.Black ,
         modifier = Modifier
            .padding(start = 16.dp)
            .constrainAs(fromText) {
               top.linkTo(timeText.bottom)
               start.linkTo(parent.start)
            }
      )
      Text(
         text = flight.fromShort ,
         fontWeight = FontWeight.SemiBold ,
         fontSize = 18.sp ,
         color = Color.Black ,
         modifier = Modifier
            .constrainAs(fromShorText) {
               top.linkTo(fromText.bottom)
               start.linkTo(fromText.start)
               end.linkTo(fromText.end)
            }
      )
      Text(
         text = flight.to ,
         fontSize = 14.sp ,
         color = Color.Black ,
         modifier = Modifier
            .padding(end = 16.dp)
            .constrainAs(toText) {
               top.linkTo(timeText.bottom)
               end.linkTo(parent.end)
            }
      )
      Text(
         text = flight.toShort ,
         fontWeight = FontWeight.SemiBold ,
         fontSize = 18.sp ,
         color = Color.Black ,
         modifier = Modifier
            .constrainAs(toShortTxt) {
               top.linkTo(toText.bottom)
               start.linkTo(toText.start)
               end.linkTo(toText.end)
            }
      )

   }


}