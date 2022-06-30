package me.ahch.image_search_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import me.ahch.core.model.Hit
import me.ahch.core.utils.tagsToArray
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
        ) {
            Author(hit.user)
            TagsList(hit.tags.tagsToArray())
        }
    }
}


@Composable
fun LandscapeSearchItem(hit: Hit, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,

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
        ) {
            Author(hit.user)
            TagsList(hit.tags.tagsToArray())
        }
    }
}

@Composable
fun TagsList(tagList: List<String>) {
    LazyRow {
        items(items = tagList) { tag ->
            Surface(
                modifier = Modifier.padding(
                    start = DefaultStandardPadding,
                    end = DefaultSmallPadding,
                    bottom = DefaultStandardPadding
                ),
                elevation = ChipsElevation,
                shape = RoundedCornerShape(ChipsRoundedCorner),
            ) {
                Text(
                    tag,
                    style = MaterialTheme.typography.caption,
                    color = Color.Black,
                    modifier = Modifier.padding(DefaultStandardPadding)
                )
            }
        }
    }
}

@Composable
fun Author(author: String) {
    Text(
        modifier = Modifier.padding(DefaultStandardPadding),
        text = stringResource(R.string.search_screen_search_item_photoby) + author,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6,
    )
}