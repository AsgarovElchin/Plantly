package asgarov.elchin.plantly.feature_scan.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalContext
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import androidx.navigation.NavController


@Composable
fun ScanScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: ScanViewModel = hiltViewModel()
    val scanState = viewModel.state.value

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission) {
        ScanContent(
            scanState = scanState,
            onImageCaptured = { uri ->
                val base64 = viewModel.encodeImage(context, uri)
                viewModel.identifyPlant(base64)
            },
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    } else {
        Text("Camera permission is required to scan plants.")
    }
}

