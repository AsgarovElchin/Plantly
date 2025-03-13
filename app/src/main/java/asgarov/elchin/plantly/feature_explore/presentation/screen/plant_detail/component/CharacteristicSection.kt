package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Fax
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.ThermostatAuto
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail

@Composable
fun CharacteristicSection(plant: PlantDetail) {
    Column(modifier = Modifier.padding(8.dp)) {
        SectionTitle( "General Information")
        CharacteristicRow(Icons.Filled.LocalFlorist, "Genus", plant.genus)
        CharacteristicRow(Icons.Filled.Category, "Type", plant.type)
        CharacteristicRow(Icons.Filled.SyncAlt, "Cycle", plant.cycle)

        SectionTitle("Care Requirements")
        CharacteristicRow(Icons.Filled.WbSunny, "Sunlight", plant.sunlight.joinToString())
        CharacteristicRow(Icons.Filled.WaterDrop, "Watering", plant.watering)
        CharacteristicRow(Icons.Filled.Timeline, "Growth Rate", plant.growthRate)

        if (plant.hardiness != null) {
            SectionTitle( "Hardiness & Temperature")
            CharacteristicRow(Icons.Filled.Thermostat, "Min Temperature", "${plant.hardiness.min}°C")
            CharacteristicRow(Icons.Filled.ThermostatAuto, "Max Temperature", "${plant.hardiness.max}°C")
        }

        SectionTitle( "Environmental Tolerance")
        CharacteristicRow(Icons.Filled.Home, "Indoor?", if (plant.indoor) "Yes" else "No")
        CharacteristicRow(Icons.Filled.Grass, "Drought Tolerant?", if (plant.droughtTolerant) "Yes" else "No")

        SectionTitle( "Edibility & Toxicity")
        CharacteristicRow(Icons.Filled.Eco, "Edible Leaf?", if (plant.edibleLeaf) "Yes" else "No")
        CharacteristicRow(Icons.Filled.Fax, "Edible Fruit?", if (plant.edibleFruit) "Yes" else "No")
        CharacteristicRow(Icons.Filled.Warning, "Poisonous to Pets?", if (plant.poisonousToPets) "Yes" else "No")
        CharacteristicRow(Icons.Filled.WarningAmber, "Poisonous to Humans?", if (plant.poisonousToHumans) "Yes" else "No")
    }
}


