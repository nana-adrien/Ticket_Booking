package empire.digiprem.ticketbooking.activities.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.ticketbooking.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(
        items : List<String> ,
        loadingIcon : Painter ,
        hint : String ,
        showLocationLoading : Boolean ,
        onSelectedItem : (String) -> Unit ,
) {
   var selectedItem by remember { mutableStateOf("") }
   var expanded by remember { mutableStateOf(false) }

   ExposedDropdownMenuBox(
      expanded = expanded ,
      onExpandedChange = {
         expanded = !expanded
      }
   ) {
      if (showLocationLoading) {
         Box(
            modifier = Modifier
               .padding(top = 8.dp)
               .fillMaxWidth()
               .background(
                  color = colorResource(R.color.lightPurPle) ,
                  shape = RoundedCornerShape(10.dp)
               )
               .height(55.dp) ,
            contentAlignment = Alignment.Center
         ) {
            CircularProgressIndicator()
         }
      } else {
         OutlinedTextField(
            value = selectedItem ,
            onValueChange = {
               selectedItem = it
            } ,
            readOnly = true ,
            modifier = Modifier
               .padding(top = 8.dp)
               .fillMaxWidth()
               .clip(RoundedCornerShape(10.dp))
               .menuAnchor() ,
            placeholder = {
               Text(
                  text = hint ,
                  color = Color.Black ,
                  fontSize = 14.sp ,
                  fontWeight = FontWeight.Bold
               )
            } ,
            leadingIcon = {
               Image(painter = loadingIcon , contentDescription = null)
            } ,
            textStyle = TextStyle(
               fontSize = 16.sp ,
               fontWeight = FontWeight.Bold
            ) ,
            colors = OutlinedTextFieldDefaults.colors(
               focusedBorderColor = Color.Transparent ,
               unfocusedBorderColor = Color.Transparent ,
               focusedContainerColor = colorResource(R.color.lightPurPle) ,
               unfocusedContainerColor = colorResource(R.color.lightPurPle)
            )
         )
         DropdownMenu(
            expanded = expanded ,
            onDismissRequest = {
               expanded = false
            }
         ) {
            items.forEach { item ->
               DropdownMenuItem(
                  text = {
                     Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically
                     ) {
                        Text(
                           text = item ,
                           fontSize = 16.sp ,
                           fontWeight = FontWeight.Bold ,
                           color = Color.Black
                        )
                     }
                  } ,
                  onClick = {
                     selectedItem = item
                     expanded = false
                     onSelectedItem(item)
                  }
               )

            }

         }

      }
   }
}