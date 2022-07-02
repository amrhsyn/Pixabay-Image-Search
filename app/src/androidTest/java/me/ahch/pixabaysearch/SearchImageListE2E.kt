package me.ahch.pixabaysearch

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.ahch.core.model.Hit
import me.ahch.pixabaysearch.repository.SearchRepositoryFake
import me.ahch.image_detail_presentation.ImageDetailScreen
import me.ahch.image_search_domain.usecase.SearchImageUseCase
import me.ahch.image_search_presentation.SearchScreen
import me.ahch.image_search_presentation.SearchViewModel
import me.ahch.pixabaysearch.navigation.Argument
import me.ahch.pixabaysearch.navigation.Route
import me.ahch.pixabaysearch.ui.theme.PixabaySearchTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalComposeUiApi
@HiltAndroidTest
class SearchImageListE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: SearchRepositoryFake
    private lateinit var getFleetListUseCase: SearchImageUseCase
    private lateinit var fleetListViewModel: SearchViewModel

    private lateinit var navController: NavHostController


    @Before
    fun setUp() {

        repositoryFake = SearchRepositoryFake()
        getFleetListUseCase = SearchImageUseCase(repositoryFake)
        fleetListViewModel = SearchViewModel(getFleetListUseCase)

        composeRule.setContent {
            PixabaySearchTheme {
                val scaffoldState = rememberScaffoldState()
                navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SEARCH_SCREEN
                    ) {
                        composable(
                            route = Route.SEARCH_SCREEN
                        ) {
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                viewModel = hiltViewModel(),
                                navigateToDetailsScreen = {
                                    val jsonHit = Uri.encode(Gson().toJson(it))
                                    navController.navigate(Route.DETAILS_SCREEN + "/${jsonHit}")
                                }
                            )

                        }
                        composable(
                            route = Route.DETAILS_SCREEN + "/{${Argument.HITS_ARGUMENT}}",
                            arguments = listOf(
                                navArgument(Argument.HITS_ARGUMENT) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val hitType = object : TypeToken<Hit>() {}.type
                            val selectedHit = Gson().fromJson<Hit>(
                                it.arguments?.getString(Argument.HITS_ARGUMENT)!!,
                                hitType
                            )
                            ImageDetailScreen(
                                scaffoldState,
                                selectedHit
                            ) {
                                navController.navigateUp()
                            }
                        }
                    }
                }

            }



        }
    }

    @Test
    fun searchImage_ClickOnItem_PressBackButton(){
        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.SEARCH_SCREEN)
        ).isTrue()


        composeRule
            .onNodeWithText("Search here... ")
            .assertExists()

        composeRule.onNodeWithTag("searchView").performTextInput("god girl boy")

        composeRule
            .onAllNodesWithText("hd wallpaper")
            .onFirst()
            .assertIsDisplayed()


        composeRule
            .onAllNodesWithText("hd wallpaper")
            .onFirst()
            .performClick()

        composeRule
            .onAllNodesWithText("Are You Sure?")
            .onFirst()
            .assertIsDisplayed()


        composeRule
            .onNodeWithTag("Yes")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.DETAILS_SCREEN)
        ).isTrue()


        //downloads count
        composeRule
            .onAllNodesWithText("605050")
            .onFirst()
            .assertIsDisplayed()


        composeRule
            .onNodeWithTag("fab")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.SEARCH_SCREEN)
        ).isTrue()
    }
}