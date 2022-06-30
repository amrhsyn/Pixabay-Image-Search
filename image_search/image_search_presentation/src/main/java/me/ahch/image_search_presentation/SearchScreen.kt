package me.ahch.image_search_presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import me.ahch.core.model.Hit
import me.ahch.core.utils.tagsToArray
import me.ahch.image_search_presentation.components.PortraitSearchItem
import me.ahch.image_search_presentation.components.SearchView

@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: SearchViewModel,
    navigateToDetailsScreen: (selectedHit: Hit) -> Unit
) {

    val searchedImages = viewModel.hitListPagingData.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()


    Column {
        SearchView(text = state.searchedValue,
            onTextChange = {
                viewModel.onEvent(SearchEvent.OnTextChange(it))
            }, onSearchClick = {
                viewModel.onEvent(SearchEvent.OnSearchClick(it))
            }, onCloseClick = {
                viewModel.onEvent(SearchEvent.OnCloseClick)
            })
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            items(
                items = searchedImages,
                key = { hit ->
                    hit.id
                }
            ) { hit ->
                PortraitSearchItem(hit!!)
            }
        }

    }


}

@ExperimentalCoilApi
@Composable
fun UnsplashItem(hit: Hit, onItemClick: () -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .clickable {
                    onItemClick()
                }
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = hit.largeImageURL,
                contentDescription = null,

                )
            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .alpha(ContentAlpha.medium),
                color = Color.Black
            ) {}
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Photo by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(hit.user)
                        }
                        append(" on pixabay")
                    },
                    color = Color.White,
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
        LazyRow {
            items(items = hit.tags.tagsToArray()) { tag ->
                Surface(
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Black
                    )
                ) {
                    Text(tag)
                }

            }
        }
    }

}
