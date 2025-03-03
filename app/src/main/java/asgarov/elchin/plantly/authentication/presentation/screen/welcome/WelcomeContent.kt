package asgarov.elchin.plantly.authentication.presentation.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.authentication.presentation.component.AuthButton
import asgarov.elchin.plantly.core.navigation.NavigationRoute


@Composable
fun WelcomeContent(navController: NavController){

    Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp)) {
        Image(
            painter = painterResource(R.drawable.plant_welcome_illustration),
            contentDescription = "welcome screen illustration"
        )
        Spacer(modifier = Modifier.height(40.dp))

        AuthButton(
            text = "Login",
            onClick = { navController.navigate(NavigationRoute.LoginRoute.route)},
            textColor = Color.White,
            buttonColor = Color.Black

        )
        Spacer(modifier = Modifier.height(30.dp))

        AuthButton(
            text = "Register",
            onClick = { navController.navigate(NavigationRoute.RegisterRoute.route) },
            textColor = Color.Black,
            buttonColor = Color.White

        )


    }
}


