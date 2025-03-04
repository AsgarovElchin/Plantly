package asgarov.elchin.plantly.onboarding.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.onboarding.domain.model.OnboardingModel
import asgarov.elchin.plantly.onboarding.presentation.component.ButtonComponent
import asgarov.elchin.plantly.onboarding.presentation.component.IndicatorComponent
import asgarov.elchin.plantly.onboarding.presentation.component.OnboardComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("RememberReturnType")
@Composable
fun OnboardingScreen(navController: NavController) {
    val viewModel: OnBoardingViewModel = hiltViewModel()

    val pages: List<OnboardingModel> = listOf(
        OnboardingModel.FirstPage, OnboardingModel.SecondPage, OnboardingModel.ThirdPage
    )

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState by rememberUpdatedState(
        when (pagerState.currentPage) {
            0 -> listOf("", "Next")
            1 -> listOf("Back", "Next")
            2 -> listOf("Back", "Start")
            else -> listOf("", "")
        }
    )

    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp).navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart){
                    ButtonComponent(text = buttonState[0],
                        backgroundColor = Color.Transparent,
                        textColor = Color.Gray
                    ) {
                        scope.launch {
                        if(pagerState.currentPage>0){
                            pagerState.animateScrollToPage(pagerState.currentPage-1)
                        }}
                    }
                }


                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    IndicatorComponent(pageSize = pages.size, currentPage = pagerState.currentPage)
                }

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd){
                    ButtonComponent(text = buttonState[1],
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary) {
                        scope.launch {
                            if(pagerState.currentPage<pages.size-1){
                                pagerState.animateScrollToPage(pagerState.currentPage+1)
                            }
                            else{
                                viewModel.markOnboardingCompleted()
                                delay(300)
                              navController.navigate("authentication_graph"){
                                  popUpTo(0){inclusive = true}
                              }

                            }
                        }
                    }
                }
            }
        },
        content = {
            Column(Modifier.padding(it)) {
                HorizontalPager(state = pagerState) {index->
                    OnboardComponent(onboardingModel = pages[index])
                }
            }
        }
    )



}

