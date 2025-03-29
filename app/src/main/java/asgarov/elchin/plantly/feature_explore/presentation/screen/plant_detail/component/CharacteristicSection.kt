package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail

@Composable
fun CharacteristicSection(plant: PlantDetail) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SectionTitle("General Information")
        CharacteristicRow(Icons.Outlined.LocalFlorist, "Genus", plant.genus)
        CharacteristicRow(Icons.Outlined.Park, "Type", plant.type)
        CharacteristicRow(Icons.Outlined.Cached, "Cycle", plant.cycle)

        SectionTitle("Care Requirements")
        CharacteristicRow(Icons.Outlined.WbSunny, "Sunlight", plant.sunlight.joinToString())
        CharacteristicRow(Icons.Outlined.Opacity, "Watering", plant.watering)
        CharacteristicRow(Icons.Outlined.Timeline, "Growth Rate", plant.growthRate)

        plant.hardiness?.let {
            SectionTitle("Hardiness & Temperature")
            CharacteristicRow(Icons.Outlined.AcUnit, "Min Temp", "${it.min}°C")
            CharacteristicRow(Icons.Outlined.Thermostat, "Max Temp", "${it.max}°C")
        }

        SectionTitle("Environmental Tolerance")
        CharacteristicRow(Icons.Outlined.Home, "Indoor?", if (plant.indoor) "Yes" else "No")
        CharacteristicRow(Icons.Outlined.WaterDrop, "Drought Tolerant?", if (plant.droughtTolerant) "Yes" else "No")

        SectionTitle("Edibility & Toxicity")
        CharacteristicRow(Icons.Outlined.Spa, "Edible Leaf?", if (plant.edibleLeaf) "Yes" else "No")
        CharacteristicRow(Icons.Outlined.Eco, "Edible Fruit?", if (plant.edibleFruit) "Yes" else "No")
        CharacteristicRow(Icons.Outlined.Pets, "Poisonous to Pets?", if (plant.poisonousToPets) "Yes" else "No")
        CharacteristicRow(Icons.Outlined.Report, "Poisonous to Humans?", if (plant.poisonousToHumans) "Yes" else "No")
    }
}
