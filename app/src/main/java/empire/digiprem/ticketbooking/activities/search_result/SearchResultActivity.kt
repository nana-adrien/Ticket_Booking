package empire.digiprem.ticketbooking.activities.search_result

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import empire.digiprem.ticketbooking.activities.search_result.components.ItemListScreen
import empire.digiprem.ticketbooking.activities.splash.StatusTopBarColor
import empire.digiprem.ticketbooking.viewmodel.MainViewModel

class SearchResultActivity : AppCompatActivity() {
   val viewModel = MainViewModel()
   private var from:String=""
   private var to:String=""


   override fun onCreate(savedInstanceState : Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      from=intent.getStringExtra("from").toString()
      to=intent.getStringExtra("to").toString()

      setContent{
         StatusTopBarColor()
         ItemListScreen(
            from=from,
            to=to,
            viewModel=viewModel,
         ) {
            finish()
         }

      }
   }
}