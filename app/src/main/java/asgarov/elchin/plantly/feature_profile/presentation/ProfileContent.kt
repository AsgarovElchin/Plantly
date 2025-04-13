package asgarov.elchin.plantly.feature_profile.presentation

import androidx.compose.runtime.Composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.authentication.presentation.component.AuthButton

@Composable
fun ProfileContent(
    onForgotPassword: () -> Unit,
    onLogout: () -> Unit,
    onChangePassword: () -> Unit,
    onLanguageClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onFeedbackClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Settings",
            fontSize = 22.sp,
            color = colors.onBackground,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SettingItem("Change Password", onClick = onChangePassword)
        HorizontalDivider(color = colors.outlineVariant)
        SettingItem("Language", onClick = onLanguageClick)
        HorizontalDivider(color = colors.outlineVariant)
        SettingItem("Notifications", onClick = onNotificationsClick)
        HorizontalDivider(color = colors.outlineVariant)
        SettingItem("Privacy Policy", onClick = onPrivacyPolicyClick)
        HorizontalDivider(color = colors.outlineVariant)
        SettingItem("Send Feedback", onClick = onFeedbackClick)

        Spacer(modifier = Modifier.height(40.dp))

        AuthButton(
            text = "Forgot Password",
            onClick = onForgotPassword,
            textColor = colors.onPrimary,
            buttonColor = colors.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthButton(
            text = "Logout",
            onClick = onLogout,
            textColor = colors.onError,
            buttonColor = colors.error
        )
    }
}

@Composable
fun SettingItem(title: String, onClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = colors.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}