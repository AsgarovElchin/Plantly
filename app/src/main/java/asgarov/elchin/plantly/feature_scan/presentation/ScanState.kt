package asgarov.elchin.plantly.feature_scan.presentation

data class ScanState(
    val isLoading: Boolean = false,
    val result: String = "",
    val error: String = ""
)

