package me.ahch.core_ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import me.ahch.core_ui.R


@Composable
fun PhotographerName(name: String) {
    Text(
        text = stringResource(R.string.shared_item_photoBy) + name,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6,
    )
}