package com.samm.ktor01.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samm.ktor01.R

data class TabItem(
    val titleResId: Int,
    val index: Int,
)

@Composable
fun BottomNavigation(
    tabs: List<TabItem> = listOf(
        TabItem(R.drawable.baseline_home_24, 0),
        TabItem(R.drawable.baseline_date_range_24, 1),
        TabItem(R.drawable.baseline_favorite_border_24, 2)
    ),
    onTabSelected: (Int) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {

        tabs.forEachIndexed { index, _ ->

            IconButton(
                onClick = {
                    onTabSelected(index)
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(painter = painterResource(id = tabs[index].titleResId), contentDescription = null)
            }
        }
    }
}

