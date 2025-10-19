package empire.digiprem.ticketbooking.activities.seat_select.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import empire.digiprem.ticketbooking.R

@Composable
fun TopSection(
        title : String ,
        modifier : Modifier = Modifier ,
        onBackClick : () -> Unit ,
) {
   ConstraintLayout(
      modifier = modifier
         .fillMaxSize()
         .background(color = colorResource(R.color.darkPurPle2))
         .padding(top = 36.dp , start = 16.dp , end = 16.dp)
   )
   {
      val (backBtn , headerTitle , worldImg) = createRefs()
      Image(
         painter = painterResource(R.drawable.back) ,
         contentDescription = null ,
         modifier = Modifier
            .clickable {
               onBackClick()
            }
            .constrainAs(backBtn) {
               top.linkTo(parent.top)
               start.linkTo(parent.start)
            }
      )

      Text(
         text =title ,
         color = Color.White ,
         textAlign = TextAlign.Center ,
         fontSize = 18.sp ,
         fontWeight = FontWeight.Bold ,
         modifier = Modifier
            .padding(start = 8.dp)
            .constrainAs(headerTitle) {
               top.linkTo(parent.top)
               start.linkTo(backBtn.end)
            }
      )

      Image(
         painter = painterResource(R.drawable.world) ,
         contentDescription = null ,
         modifier = Modifier.constrainAs(worldImg) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
         }
      )
   }

}