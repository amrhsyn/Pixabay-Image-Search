package me.ahch.image_search_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import me.ahch.core.model.Hit
import me.ahch.core.utils.tagsToArray
import me.ahch.core_ui.components.PhotographerName
import me.ahch.core_ui.components.TagList
import me.ahch.core_ui.util.*
import me.ahch.image_search_presentation.R

@Composable
fun PortraitSearchItem(hit: Hit, onItemClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().clickable { onItemClick() }
    ) {
        AsyncImage(
            model = hit.webFormatURL,
            contentDescription = stringResource(R.string.search_screen_search_item_an_image_by) + hit.user + stringResource(
                R.string.search_screen_search_item_with
            ) + hit.tags + stringResource(R.string.search_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeight)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = DefaultStandardPadding)
        ) {
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            PhotographerName(hit.user)
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            TagList(hit.tags.tagsToArray())
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
        }
    }
}


@Composable
fun LandscapeSearchItem(hit: Hit, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

        ) {
        AsyncImage(
            model = hit.webFormatURL,
            contentDescription = stringResource(R.string.search_screen_search_item_an_image_by) + hit.user + stringResource(
                R.string.search_screen_search_item_with
            ) + hit.tags + stringResource(R.string.search_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeightLandscape)
                .weight(1F)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .weight(2F)
                .height(SearchListItemImageHeightLandscape)
                .align(Alignment.CenterVertically)
                .padding(start = DefaultStandardPadding)
        ) {
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            PhotographerName(hit.user)
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            TagList(hit.tags.tagsToArray())
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
        }
    }
}
