package asgarov.elchin.plantly.onboarding.presentation.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.onboarding.domain.model.OnboardingModel

@Composable
fun OnboardComponent(onboardingModel: OnboardingModel){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.size(70.dp))

        Image(painter = painterResource(id = onboardingModel.image),
            contentDescription = "onboarding image",
            modifier = Modifier.fillMaxWidth().padding(30.dp,0.dp),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.size(50.dp))

        Text(text = onboardingModel.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.size(30.dp))

        Text(text = onboardingModel.description,
            modifier = Modifier.fillMaxWidth().padding(15.dp,0.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 22.sp),
            color = MaterialTheme.colorScheme.onSurface)

        Spacer(modifier = Modifier.size(10.dp))

    }
}