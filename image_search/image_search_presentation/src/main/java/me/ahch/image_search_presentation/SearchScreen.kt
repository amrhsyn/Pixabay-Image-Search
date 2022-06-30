package me.ahch.image_search_presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import me.ahch.core.model.Hit
import me.ahch.core_ui.util.DefaultStandardPadding
import me.ahch.core_ui.util.SearchListItemsSpace
import me.ahch.image_search_presentation.components.LandscapeSearchItem
import me.ahch.image_search_presentation.components.PortraitSearchItem
import me.ahch.image_search_presentation.components.SearchView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel,
    navigateToDetailsScreen: (selectedHit: Hit) -> Unit
) {

    val searchedImages = viewModel.hitListPagingData.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()
    val configuration = LocalConfiguration.current

    if (state.isDialogOpen) {
        AlertDialog(onDismissRequest = { viewModel.onEvent(SearchEvent.OnAlertDialogDismiss) },
            title = {
                Text(text = stringResource(R.string.search_screen_dialog_title))
            },
            text = {
                Text(stringResource(R.string.search_screen_dialog_message))
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = DefaultStandardPadding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                            .padding(DefaultStandardPadding),
                        onClick = {
                            navigateToDetailsScreen(state.selectedHit)
                            viewModel.onEvent(SearchEvent.OnAlertDialogApply)
                        }
                    ) {
                        Text(stringResource(R.string.search_screen_dialog_yes))
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.5F)
                            .weight(1F)
                            .padding(DefaultStandardPadding),
                        onClick = {
                            viewModel.onEvent(SearchEvent.OnAlertDialogDismiss)
                        }
                    ) {
                        Text(stringResource(R.string.search_screen_dialog_no))
                    }
                }
            })
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            SearchView(text = state.searchedValue,
                onTextChange = {
                    viewModel.onEvent(SearchEvent.OnTextChange(it))
                }, onSearchClick = {
                    viewModel.onEvent(SearchEvent.OnSearchClick(it))
                }, onCloseClick = {
                    viewModel.onEvent(SearchEvent.OnCloseClick)
                })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.7F else
                            1F
                    )
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = searchedImages,
                    key = { hit ->
                        hit.id
                    }
                ) { hit ->
                    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) LandscapeSearchItem(
                        hit!!
                    ) {
                        viewModel.onEvent(SearchEvent.OnSearchItemClick(hit))
                    }
                    else PortraitSearchItem(hit!!) {
                        viewModel.onEvent(SearchEvent.OnSearchItemClick(hit))
                    }
                }
            }
        }

    }

}
