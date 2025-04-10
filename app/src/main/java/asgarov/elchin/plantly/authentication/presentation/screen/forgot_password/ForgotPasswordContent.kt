package asgarov.elchin.plantly.authentication.presentation.screen.forgot_password

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import asgarov.elchin.plantly.authentication.presentation.component.AuthButton
import asgarov.elchin.plantly.authentication.presentation.component.AuthTextField
import asgarov.elchin.plantly.authentication.presentation.component.AuthTitle
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun ForgotPasswordContent(
    email: String,
    onEmailChange: (String) -> Unit,
    onSendCodeClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    isLoading: Boolean,
    errorMessage: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 70.dp)
    ) {
        AuthTitle(title = "Forgot Password?")
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Don't worry! It occurs. Please enter the email address linked with your account.",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            label = "Enter your email",
            value = email,
            onValueChange = onEmailChange,
            isPassword = false
        )

        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        AuthButton(
            text = if (isLoading) "Sending..." else "Send Code",
            textColor = Color.White,
            buttonColor = Color.Black,
            onClick = onSendCodeClick
        )

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
                    text = "Remember Password? ",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    color = Color(0xFF00ACC1),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }
        }
    }
}