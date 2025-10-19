package empire.digiprem.ticketbooking.activities.dashboard


import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import empire.digiprem.ticketbooking.R
import empire.digiprem.ticketbooking.activities.dashboard.components.DatePickerScreen
import empire.digiprem.ticketbooking.activities.dashboard.components.DropDownList
import empire.digiprem.ticketbooking.activities.dashboard.components.MyBottomBar
import empire.digiprem.ticketbooking.activities.dashboard.components.PassengerCounter
import empire.digiprem.ticketbooking.activities.dashboard.components.TopBar
import empire.digiprem.ticketbooking.activities.search_result.SearchResultActivity
import empire.digiprem.ticketbooking.activities.splash.StatusTopBarColor
import empire.digiprem.ticketbooking.activities.splash.component.GradientButton
import empire.digiprem.ticketbooking.domain.LocationModel
import empire.digiprem.ticketbooking.viewmodel.MainViewModel

class DashboardActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState : Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         MainScreen()
      }
   }
}

@Composable
@Preview
fun MainScreen() {
   val context= LocalContext.current
   val locations = remember { mutableStateListOf<LocationModel>() }
   val viewModel = MainViewModel()
   var showLocationLoading by remember { mutableStateOf(true) }
   var from : String = ""
   var to : String = ""
   var classes : String = ""
   var adultPassengerString =0
   var childPassengerString = 0

   StatusTopBarColor()
   LaunchedEffect(Unit) {
      viewModel.loadLocalisation().observeForever { result ->
         locations.clear()
         locations.addAll(result)
         showLocationLoading = false
      }
   }
   Scaffold(
      bottomBar = { MyBottomBar() } ,
   ) { paddingValues ->
      LazyColumn(
         modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurPle2))
            .padding(paddingValues) ,
      ) {
         item { TopBar() }
         item {
            Column(
               modifier = Modifier
                  .padding(32.dp)
                  .background(
                     colorResource(R.color.darkPurPle) ,
                     shape = RoundedCornerShape(20.dp)
                  )
                  .fillMaxSize()
                  .padding(vertical = 16.dp , horizontal = 24.dp) ,
            ) {
               // from Section
               YellowTitle(text = "Form")
               val locationNames : List<String> = locations.map { it.Name }
               DropDownList(
                  items = locationNames,
                  loadingIcon = painterResource(R.drawable.from_ic) ,
                  hint = "Select Origine" ,
                  showLocationLoading = showLocationLoading ,
               ) { selectedItem ->
                  from = selectedItem
               }
               Spacer(modifier = Modifier.height(16.dp))

               // to Section
               YellowTitle(text = "To")

               DropDownList(
                  items = locationNames ,
                  loadingIcon = painterResource(R.drawable.to_ic) ,
                  hint = "Select Destination" ,
                  showLocationLoading = showLocationLoading ,
               ) { selectedItem ->
                  to = selectedItem
               }
               Spacer(modifier = Modifier.height(16.dp))
               // passenger Counter
               YellowTitle(text = "Passenger")
               Row(modifier = Modifier.fillMaxWidth()) {
                  PassengerCounter(
                     title = "Adult" ,
                     modifier = Modifier.weight(1f)
                  ) {
                     adultPassengerString = it.toInt()
                  }
                  Spacer(modifier = Modifier.width(16.dp))
                  PassengerCounter(
                     title = "Child" ,
                     modifier = Modifier.weight(1f)
                  ) {
                     adultPassengerString = it.toInt()
                  }
               }
               Spacer(modifier = Modifier.height(16.dp))

               //  calendar section
               Row(modifier = Modifier.fillMaxWidth()){
                  YellowTitle(text = "Departure date", modifier = Modifier.weight(1f))
                  Spacer(modifier = Modifier.width(16.dp))
                  YellowTitle(text = "Return date", modifier = Modifier.weight(1f))
               }
               DatePickerScreen(Modifier.weight(1f))

               Spacer(modifier = Modifier.height(16.dp))

               // classes  Section
               YellowTitle(text = "Class")
               val classItems = listOf("Business class" , "First class" , "Economy Class")

               DropDownList(
                  items = classItems ,
                  loadingIcon = painterResource(R.drawable.seat_black_ic) ,
                  hint = "Select class" ,
                  showLocationLoading = showLocationLoading ,
               ) { selectedItem ->
                  classes = selectedItem
               }
               Spacer(modifier = Modifier.height(16.dp))

               //search Button
               GradientButton(
                  onclick = {
                     val intent= Intent(context , SearchResultActivity::class.java).apply {
                        putExtra("to",to)
                        putExtra("from",from)
                        putExtra("numPassenger",adultPassengerString+childPassengerString)
                     }
                     startActivity(context,intent,null)
                  },
                  text = "Search"
               )

            }
         }
      }
   }
}

@Composable
fun YellowTitle(text : String , modifier : Modifier = Modifier) {
   Text(
      text = text ,
      color = colorResource(R.color.orange) ,
      fontWeight = FontWeight.SemiBold ,
      modifier = modifier
   )
}