package asgarov.elchin.plantly.authentication.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTitle(
    title:String,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(horizontal = 24.dp)
){
    Text(text = title, fontSize = 24.sp, modifier = modifier, overflow = TextOverflow.Ellipsis)

}

@Preview
@Composable
fun AuthTitlePreview(){
    AuthTitle(title = "Welcome back! Glad to see you, Again!")
}