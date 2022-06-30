package me.ahch.image_detail_presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import me.ahch.core.model.Hit
import me.ahch.core.utils.tagsToArray
import me.ahch.core_ui.components.TagList
import me.ahch.core_ui.util.DefaultLargePadding
import me.ahch.core_ui.util.DefaultSmallPadding
import me.ahch.core_ui.util.DefaultStandardPadding
import me.ahch.core_ui.util.SearchListItemImageHeight
import me.ahch.image_detail_presentation.Components.IconText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImageDetailScreen(
    scaffoldState: ScaffoldState,
    hit: Hit, onBackPress: () -> Unit
) {
    val configuration = LocalConfiguration.current
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            FloatingActionButton(
                modifier = Modifier.padding(
                    DefaultLargePadding
                ),
                onClick = { onBackPress() }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        }
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) LandscapeBody(hit)
        else PortraitBody(hit)
    }

}

@Composable
fun PortraitBody(hit: Hit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = hit.largeImageURL,
            contentDescription = stringResource(R.string.image_detail_screen_search_item_an_image_by) + hit.user + stringResource(
                R.string.image_detail_screen_search_item_with
            ) + hit.tags + stringResource(R.string.image_detail_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeight)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(DefaultLargePadding)
        ) {
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_person, hit.user)
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_download, hit.downloads.toString())
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_like, hit.likes.toString())
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_comment, hit.comments.toString())
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            TagList(hit.tags.tagsToArray())
        }
    }
}



@Composable
fun LandscapeBody(hit: Hit) {
    Row(
        modifier = Modifier.fillMaxHeight(0.6F),
        verticalAlignment = Alignment.Top,
        ) {
        AsyncImage(
            model = hit.largeImageURL,
            contentDescription = stringResource(R.string.image_detail_screen_search_item_an_image_by) + hit.user + stringResource(
                R.string.image_detail_screen_search_item_with
            ) + hit.tags + stringResource(R.string.image_detail_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .weight(2F)
                .align(Alignment.Top)
        )
        Column(
            modifier = Modifier
                .weight(3F)
                .align(Alignment.Top)
                .padding(DefaultLargePadding)
        ) {
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_person, hit.user)
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_download, hit.downloads.toString())
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_like, hit.likes.toString())
            Spacer(modifier = Modifier.height(DefaultSmallPadding))
            IconText(R.drawable.ic_comment, hit.comments.toString())
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            TagList(hit.tags.tagsToArray())
        }
    }
}