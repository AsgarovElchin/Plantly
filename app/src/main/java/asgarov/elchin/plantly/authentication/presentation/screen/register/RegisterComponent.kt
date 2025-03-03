package asgarov.elchin.plantly.authentication.presentation.screen.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.authentication.presentation.component.AuthButton
import asgarov.elchin.plantly.authentication.presentation.component.AuthTextField
import asgarov.elchin.plantly.authentication.presentation.component.AuthTitle
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun RegisterContent(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp)) {
        AuthTitle(title = "Hello! Register to get started")
        Spacer(modifier = Modifier.height(30.dp))
        var userName by remember { mutableStateOf("") }
        AuthTextField(
            label = "Username",
            value = userName,
            onValueChange = { userName = it},
            isPassword = false
        )
        Spacer(modifier = Modifier.height(10.dp))
        var email by remember { mutableStateOf("") }
        AuthTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it},
            isPassword = false
        )
        Spacer(modifier = Modifier.height(10.dp))
        var password by remember { mutableStateOf("") }
        AuthTextField(
            label = "Password",
            value = password,
            onValueChange = { password = it},
            isPassword = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        var confirmPassword by remember { mutableStateOf("") }
        AuthTextField(
            label = "Confirm Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it},
            isPassword = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Forgot password?", modifier = Modifier.fillMaxWidth().padding(end = 24.dp).clickable {
            navController.navigate(NavigationRoute.ForgotPasswordRoute.route)
        }, textAlign = TextAlign.End, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        AuthButton(text = "Register",
            textColor = Color.White,
            buttonColor = Color.Black,
            onClick = {})

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Color.LightGray
            )
            Text(
                text = "Or Register with",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray,
                fontSize = 14.sp
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        AuthButton(text = "Register with Google",
            textColor = Color.Black,
            buttonColor = Color.White,
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified
                )
            })


        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.BottomCenter),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Login Now",
                    fontSize = 14.sp,
                    color = Color(0xFF00ACC1),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate(NavigationRoute.LoginRoute.route)
                    }
                )
            }
        }
    }

}