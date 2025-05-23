package asgarov.elchin.plantly.authentication.presentation.screen.create_new_password

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun CreateNewPasswordContent(
    newPassword: String,
    confirmPassword: String,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onResetPasswordClick: () -> Unit,
    isLoading: Boolean,
    errorMessage: String?
) {
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp)) {
        AuthTitle(title = "Create new password")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Your new password must be unique from those previously used.",
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
            label = "New Password",
            value = newPassword,
            onValueChange = onNewPasswordChange,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthTextField(
            label = "Confirm Password",
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            isPassword = true
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
            text = if (isLoading) "Resetting..." else "Reset Password",
            textColor = Color.White,
            buttonColor = Color.Black,
            onClick = onResetPasswordClick
        )
    }
}