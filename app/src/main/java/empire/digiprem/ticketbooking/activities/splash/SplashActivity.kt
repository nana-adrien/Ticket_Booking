package empire.digiprem.ticketbooking.activities.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import empire.digiprem.ticketbooking.R
import empire.digiprem.ticketbooking.activities.dashboard.DashboardActivity
import empire.digiprem.ticketbooking.activities.splash.component.GradientButton
import kotlinx.coroutines.withTimeout

class SplashActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState : Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         SplashScreen {
            startActivity(Intent(this , DashboardActivity::class.java))
         }
      }
   }
}

@Preview
@Composable
fun SplashScreen(onGetStartedClick : () -> Unit = {}) {
   StatusTopBarColor()
   Column(modifier = Modifier.fillMaxSize()) {
      ConstraintLayout() {
         val (backgroundIm , title , subject , startBtn) = createRefs()

         Image(
            painter = painterResource(R.drawable.splash_bg) ,
            contentDescription = null ,
            modifier = Modifier
               .constrainAs(
                  backgroundIm
               ) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
               }
               .fillMaxSize()
         )
         val styledText = buildAnnotatedString {
            append("Discover your\nDream")
            withStyle(style = SpanStyle(color = colorResource(R.color.orange))) {
               append("Tickets")
            }
            append("\nEasily")
         }
         androidx.compose.material.Text(
            text = styledText ,
            fontSize = 53.sp ,
            fontWeight = FontWeight.Bold ,
            color = Color.White ,
            modifier = Modifier
               .padding(top = 32.dp)
               .padding(horizontal = 16.dp)
               .constrainAs(title) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
               }
         )
         Text(
            text = stringResource(R.string.subtitle_splash) ,
            fontSize = 18.sp ,
            fontWeight = FontWeight.SemiBold ,
            color = colorResource(R.color.orange) ,
            modifier = Modifier
               .padding(top = 32.dp , start = 16.dp)
               .constrainAs(subject) {
                  top.linkTo(title.bottom)
                  start.linkTo(parent.start)
               }
         )
         Box(
            modifier = Modifier.constrainAs(
               startBtn
            ) {
               bottom.linkTo(parent.bottom)
            }) {
            GradientButton(onclick = onGetStartedClick , padding = 32)
         }
      }
   }
}

@Composable
fun StatusTopBarColor() {
   val systemUiController = rememberSystemUiController()
   SideEffect {
      systemUiController.setStatusBarColor(
         color = Color.White ,
         darkIcons = true
      )
   }
}