package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthButton(
    text:String,
    onClick:()-> Unit,
    textColor:Color,
    buttonColor:Color,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
    icon: (@Composable () -> Unit)? = null ){

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = textColor),
        shape = RoundedCornerShape(10.dp)
    ){ Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.invoke()
            if (icon != null) Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 6.dp))
        }


}}


@Preview
@Composable
fun AuthButtonPreview1(){
    AuthButton(text = "Login",
        textColor = Color.White,
        buttonColor = Color.Black,
        onClick = {})
}


@Preview
@Composable
fun AuthButtonPreview2(){
    AuthButton(text = "Register",
        textColor = Color.Black,
        buttonColor = Color.White,
        onClick = {})
}


