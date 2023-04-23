package com.samm.ktor01.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Title(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 15.dp),
        style = MaterialTheme.typography.headlineMedium
    )
}