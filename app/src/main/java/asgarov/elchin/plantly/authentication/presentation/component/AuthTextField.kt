package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.max


@Composable
fun AuthTextField(label:String, value:String, onValueChange:(String)-> Unit, isPassword: Boolean = false, modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)){
    var passwordVisible by  remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                       imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                      contentDescription = if (passwordVisible) "Hide password" else "Show password"
                  )
             }
            }
        },
        shape = RoundedCornerShape(10.dp),
        maxLines = 1
    )

}


@Preview
@Composable
fun AuthTextFieldPreview1() {
    var text1 by remember { mutableStateOf("") }
    AuthTextField(
        label = "Enter your email",
        value = text1,
        onValueChange = { text1 = it},
        isPassword = false
    )
}

@Preview
@Composable
fun AuthTextFieldPreview2() {
    var text2 by remember { mutableStateOf("") }
    AuthTextField(
        label = "Enter your password",
        value = text2,
        onValueChange = { text2 = it},
        isPassword = true
    )
}

