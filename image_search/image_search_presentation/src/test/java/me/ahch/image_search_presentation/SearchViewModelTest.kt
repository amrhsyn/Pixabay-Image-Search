package me.ahch.image_search_presentation

import androidx.paging.*
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.ahch.image_search_domain.usecase.SearchImageUseCase
import me.ahch.rule.MainDispatcherRule
import me.ahch.test_shared.TestData
import me.ahch.test_shared.TestData.sampleTestQuery1
import me.ahch.test_shared.TestData.sampleTestQuery2
import me.ahch.test_shared.TestData.sampleTestQuery3

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {


    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    //system under test
    private lateinit var searchViewModel: SearchViewModel
    
    private lateinit var searchImageUseCase: SearchImageUseCase

    @Before
    fun setup() {
        searchImageUseCase = mockk(relaxed = true)
        searchViewModel = SearchViewModel(searchImageUseCase)
    }

    @Test
    fun `searchImage less than 3 chars do nothing`() = runTest {
        searchViewModel.searchImage(sampleTestQuery3)

        coVerify(inverse = true) { searchImageUseCase.invoke("") }
    }

    @Test
    fun `searchImage more than 3 chars set value successfully`() = runTest {
        val pagingData = PagingData.from(
            TestData.hitList
        )
        coEvery { searchImageUseCase.invoke("") } returns flowOf(pagingData)

        searchViewModel.searchImage(sampleTestQuery1)

        searchViewModel.hitListPagingData.first()

        searchViewModel.hitListPagingData.value.map {
            Truth.assertThat(it.user).isEqualTo(TestData.hitList.first().user)
        }
    }


    @Test
    fun `onEvent calls with onTextChange event changes the state value calls searchImage`() = runTest {
        
        val v = SearchEvent.OnTextChange(sampleTestQuery2)

       
        searchViewModel.onEvent(v)

       
        val result = searchViewModel.state.first()
        verify { searchViewModel.searchImage() }
        Truth.assertThat(TestData.sampleTestQuery2).isEqualTo(result.searchedValue)

    }

    @Test
    fun `onEvent calls with OnSearchClick event changes the state value calls searchImage`() = runTest {
        
        val v = SearchEvent.OnSearchClick(sampleTestQuery2)

       
        searchViewModel.onEvent(v)

       
        val result = searchViewModel.state.first()
        verify { searchViewModel.searchImage() }
        Truth.assertThat(TestData.sampleTestQuery2).isEqualTo(result.searchedValue)

    }

    @Test
    fun `onEvent calls with OnCloseClick event changes the state value to null`() = runTest {
        
        val v = SearchEvent.OnCloseClick

       
        searchViewModel.onEvent(v)

       
        val result = searchViewModel.state.first()
        Truth.assertThat(result.searchedValue).isEqualTo("")

    }

    @Test
    fun `onEvent calls with OnAlertDialogDismiss event changes the isDialogOpen state to false`() = runTest {
        
        val v = SearchEvent.OnAlertDialogDismiss

       
        searchViewModel.onEvent(v)

       
        val result = searchViewModel.state.first()
        Truth.assertThat(result.isDialogOpen).isEqualTo(false)

    }

    @Test
    fun `onEvent calls with OnAlertDialogApply event changes the isDialogOpen state to false`() = runTest {
        
        val v = SearchEvent.OnAlertDialogApply

       
        searchViewModel.onEvent(v)

       
        val result = searchViewModel.state.first()
        Truth.assertThat(result.isDialogOpen).isEqualTo(false)

    }

    @Test
    fun `onEvent calls with OnSearchItemClick event changes the selectedHit state and isDialogOpen`() = runTest {
        
        val v = SearchEvent.OnSearchItemClick(TestData.hit1)

       
        searchViewModel.onEvent(v)


        val result = searchViewModel.state.first()
        Truth.assertThat(result.selectedHit).isEqualTo(TestData.hit1)
        Truth.assertThat(result.isDialogOpen).isEqualTo(true)

    }
}
