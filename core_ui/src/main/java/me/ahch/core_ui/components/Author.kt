package me.ahch.core_ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import me.ahch.core_ui.R
import me.ahch.core_ui.util.ChipsElevation
import me.ahch.core_ui.util.ChipsRoundedCorner
import me.ahch.core_ui.util.DefaultSmallPadding
import me.ahch.core_ui.util.DefaultStandardPadding


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