package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp


@Composable
fun OtpInputRow(
    code: String,
    onCodeChange: (String) -> Unit
) {
    val otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusRequesters = List(6) { FocusRequester() }

    LaunchedEffect(code) {
        if (code.length == 6) {
            for (i in 0..5) {
                otpValues[i] = code[i].toString()
            }
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        otpValues.forEachIndexed { index, _ ->
            OTPTextField(
                value = otpValues[index],
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otpValues[index] = newValue
                        val joined = otpValues.joinToString("")
                        onCodeChange(joined)

                        if (newValue.isNotEmpty() && index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                nextFocusRequester = focusRequesters.getOrNull(index + 1),
                modifier = Modifier.focusRequester(focusRequesters[index])
            )
        }
    }
}
