package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.feature_explore.domain.model.Category

@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clickable { onCategoryClick(category) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getCategoryImage(category.categoryName)),
                contentDescription = category.categoryName,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.categoryName,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}



fun getCategoryImage(categoryName: String): Int {
    return when (categoryName) {
        "Dracaena" -> R.drawable.dracaena
        else -> R.drawable.dracaena
    }
}

