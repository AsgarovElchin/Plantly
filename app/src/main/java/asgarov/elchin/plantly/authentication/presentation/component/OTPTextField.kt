package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OTPTextField(
    value: String,
    onValueChange: (String) -> Unit,
    nextFocusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1) {
                onValueChange(it)
                if (it.isNotEmpty()) {
                    nextFocusRequester?.requestFocus()
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
        ),
        textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
        modifier = modifier
            .size(50.dp)
            .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
            .focusRequester(focusRequester)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp),
        singleLine = true
    )
}
