package empire.digiprem.ticketbooking.activities.seat_select.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import empire.digiprem.ticketbooking.R
import empire.digiprem.ticketbooking.domain.FlightModel
import kotlinx.coroutines.CoroutineScope
import kotlin.times


enum class SeatStatus {
   AVAILABLE ,
   SELECTED ,
   UNAVAILABLE ,
   EMPTY
}

data class Seat(
        var status : SeatStatus ,
        var name : String ,
)

@Composable
fun SeatListScreen(
        flight : FlightModel ,
        onBackClick : () -> Unit ,
        onConfirm : (FlightModel) -> Unit ,
) {

   val context = LocalContext.current
   val seatList = remember { mutableStateListOf<Seat>() }
   val selectedSeatsNames = remember { mutableStateListOf<String>() }

   var seatCount by remember { mutableStateOf(0) }
   var totalPrice by remember { mutableDoubleStateOf(0.0) }


   LaunchedEffect(
      flight
   ) {
      seatList.clear()
      seatList.addAll(generateSeatList(flight))
      seatCount = selectedSeatsNames.size
      totalPrice = seatCount * flight.price
   }
   fun updatePriceAndCount(){
      seatCount = selectedSeatsNames.size
      totalPrice = seatCount * flight.price
   }
   ConstraintLayout(
      modifier = Modifier
         .fillMaxSize()
         .background(color = colorResource(R.color.darkPurPle2))
   ) {
      val (topSection , middleSection , bottomSection) = createRefs()
      TopSection(
         title = "Seat Selection" ,
         modifier = Modifier.constrainAs(topSection) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
         } ,
         onBackClick = onBackClick
      )
      // middle section

      ConstraintLayout(
         modifier = Modifier
            .padding(top = 100.dp)
            .constrainAs(middleSection) {
               top.linkTo(parent.top)
               start.linkTo(parent.start)
               end.linkTo(parent.end)
            })
      {

         val (airplane , seatGrid) = createRefs()
         Image(
            painter = painterResource(R.drawable.airple_seat) ,
            contentDescription = null ,
            modifier = Modifier.constrainAs(airplane) {
               top.linkTo(parent.top)
               start.linkTo(parent.start)
               end.linkTo(parent.end)
            }
         )

         LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier=Modifier.padding(top=240.dp).padding(horizontal = 64.dp)
               .constrainAs(seatGrid) {
                  top.linkTo(parent.top)
                  start.linkTo(airplane.start)
                  end.linkTo(airplane.end)
               }
         ) {
            itemsIndexed(seatList) { index,item->
               SeatItem(item) {
                  when(item.status){
                     SeatStatus.AVAILABLE ->{
                        item.status=SeatStatus.SELECTED
                        selectedSeatsNames.add(item.name)
                     }
                     SeatStatus.SELECTED -> {
                        item.status=SeatStatus.AVAILABLE
                        selectedSeatsNames.remove(item.name)//
                     }
                     else -> {

                     }
                  }
                  updatePriceAndCount()
               }
            }
         }
      }
      BottomSection(
         seatCount = seatCount ,
         selectedSeats = selectedSeatsNames.joinToString(",") ,
         totalPrice = totalPrice,
         modifier=Modifier.constrainAs(bottomSection) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
         }
      ){
         if (seatCount > 0) {
            flight.passenger=selectedSeatsNames.joinToString(",")
            flight.price=totalPrice
            onConfirm(flight)
         }else{
            Toast.makeText(context,"Please select your seat",Toast.LENGTH_SHORT).show()
         }
      }

   }


}

private fun CoroutineScope.generateSeatList(flight : FlightModel) : List<Seat> {
   val seatList = mutableListOf<Seat>()
   val numberSeat = flight.numberSeat + (flight.numberSeat / 7) + 1
   val seatAlpabetMap = mapOf(
      0 to "A" ,
      1 to "B" ,
      2 to "C" ,
      3 to "D" ,
      4 to "F" ,
      5 to "G" ,
      6 to "H" ,
   )
   var row = 0
   for (i in 0 until numberSeat) {
      if (i % 7 == 0) {
         row++
      }
      if (i % 7 == 3) {
         seatList.add(Seat(SeatStatus.EMPTY , "$row"))
      } else {
         val seatName = seatAlpabetMap[i % 7] + row
         val seatStatus = if (flight.reservedSeats.contains(seatName)) {
            SeatStatus.UNAVAILABLE
         } else {
            SeatStatus.AVAILABLE
         }
         seatList.add(Seat(seatStatus , seatName))
      }
   }
   return seatList
}
