package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp


@Composable
fun OtpInputRow() {
    val otpValues = remember { mutableStateListOf("", "", "", "") }
    val focusRequesters = List(4) { FocusRequester() }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        otpValues.forEachIndexed { index, _ ->
            OTPTextField(
                value = otpValues[index],
                onValueChange = { newValue ->
                    otpValues[index] = newValue
                },
                nextFocusRequester = focusRequesters.getOrNull(index + 1),
                modifier = Modifier.focusRequester(focusRequesters[index])
            )
        }
    }
}
