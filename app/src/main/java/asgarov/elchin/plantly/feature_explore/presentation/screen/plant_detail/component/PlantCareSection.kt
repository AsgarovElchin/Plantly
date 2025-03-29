package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.feature_explore.domain.model.CareSection

@Composable
fun PlantCareSection(careSections: List<CareSection>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        careSections.forEach { careSection ->
            var expanded by remember { mutableStateOf(false) }

            val icon = when (careSection.type.lowercase()) {
                "watering" -> Icons.Outlined.Opacity
                "sunlight" -> Icons.Outlined.WbSunny
                "pruning" -> Icons.Outlined.ContentCut
                else -> Icons.Default.Info
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .animateContentSize()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = icon,
                            contentDescription = careSection.type,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = careSection.type.replaceFirstChar { it.uppercase() },
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (expanded) careSection.description else careSection.description.take(100) + "...",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (expanded) "Show Less" else "Read More",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}