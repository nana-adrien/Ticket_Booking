package empire.digiprem.ticketbooking.activities.seat_select

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import empire.digiprem.ticketbooking.activities.seat_select.components.SeatListScreen
import empire.digiprem.ticketbooking.activities.splash.StatusTopBarColor
import empire.digiprem.ticketbooking.domain.FlightModel


class SeatSelectActivity : AppCompatActivity() {

   private lateinit var flight : FlightModel

   override fun onCreate(savedInstanceState : Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      flight = intent.getSerializableExtra("flight") as FlightModel
      setContent {
         StatusTopBarColor()
         SeatListScreen(
            flight ,
            onBackClick = {finish()} ,
            onConfirm = {

            })
      }
   }
}