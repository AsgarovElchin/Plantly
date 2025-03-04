package asgarov.elchin.plantly.authentication.presentation.screen.password_changed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.authentication.presentation.component.AuthButton
import asgarov.elchin.plantly.authentication.presentation.component.AuthTitle
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun PasswordChangedContent(navController: NavController){

    Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.success_icon),
            contentDescription = "welcome screen illustration"
        )
        Spacer(modifier = Modifier.height(20.dp))
        AuthTitle(title = "Password Changed!", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Your password has been changed successfully.", overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Start, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(30.dp))
        AuthButton(text = "Back to Login",
            textColor = Color.White,
            buttonColor = Color.Black,
            onClick = {
                navController.navigate(NavigationRoute.LoginRoute.route)
            })

    }


}