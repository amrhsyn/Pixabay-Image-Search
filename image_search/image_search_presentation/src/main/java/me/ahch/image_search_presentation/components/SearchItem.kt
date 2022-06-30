package me.ahch.image_search_presentation.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.ahch.core.model.Hit
import me.ahch.core.utils.tagsToArray
import me.ahch.core_ui.util.*
import me.ahch.image_search_presentation.R

@Composable
fun PortraitSearchItem(hit: Hit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = hit.webFormatURL,
            contentDescription = stringResource(R.string.search_screen_search_item_an_image_by) +hit.user+ stringResource(
                            R.string.search_screen_search_item_with) +hit.tags+ stringResource(R.string.search_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeight)
                .fillMaxWidth()
        )
        Column(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .align(Alignment.Start)) {
            Text(

                modifier = Modifier.padding(DefaultStandardPadding),
                text = stringResource(R.string.search_screen_search_item_photoby) + hit.user,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,

                )
            LazyRow {
                items(items = hit.tags.tagsToArray()) { tag ->
                    Surface(
                        modifier = Modifier.padding(start = DefaultStandardPadding, end = DefaultSmallPadding, bottom = DefaultStandardPadding),
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
    }
}

@Composable
fun LandscapeSearchItem(hit: Hit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = hit.webFormatURL,
            contentDescription = stringResource(R.string.search_screen_search_item_an_image_by) +hit.user+ stringResource(
                R.string.search_screen_search_item_with) +hit.tags+ stringResource(R.string.search_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeight)
                .fillMaxWidth()
        )
        Column(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .align(Alignment.Start)) {
            Text(

                modifier = Modifier.padding(DefaultStandardPadding),
                text = stringResource(R.string.search_screen_search_item_photoby) + hit.user,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,

                )
            LazyRow {
                items(items = hit.tags.tagsToArray()) { tag ->
                    Surface(
                        modifier = Modifier.padding(start = DefaultStandardPadding, end = DefaultSmallPadding, bottom = DefaultStandardPadding),
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
    }
}