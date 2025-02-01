package by.freiding.keepit.ui.screen.links.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.freiding.keepit.domain.model.PageData
import by.freiding.keepit.domain.model.RequestResultState
import by.freiding.keepit.domain.usecase.UseCaseResult
import by.freiding.keepit.domain.usecase.links.FetchPageDataByLinkUseCase
import by.freiding.keepit.domain.usecase.links.FetchPageDataByLinkUseCaseParams
import by.freiding.keepit.domain.usecase.links.SavePageDataToStorageUseCase
import by.freiding.keepit.domain.usecase.links.SavePageDataToStorageUseCaseParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddLinkScreenViewModel(
    private val fetchPageDataByLinkUseCase: FetchPageDataByLinkUseCase,
    private val savePageDataToStorageUseCase: SavePageDataToStorageUseCase,
) : ViewModel() {

    private val _pageData: MutableStateFlow<RequestResultState<PageData>> =
        MutableStateFlow(RequestResultState.none())
    val pageData: StateFlow<RequestResultState<PageData>> = _pageData.asStateFlow()

    private val _savingState: MutableStateFlow<RequestResultState<Nothing>> =
        MutableStateFlow(RequestResultState.None)
    val savingState: StateFlow<RequestResultState<Nothing>> = _savingState.asStateFlow()

    private val _url: MutableStateFlow<String> = MutableStateFlow("")
    val url: StateFlow<String> = _url.asStateFlow()

    fun onSaveClick() {
        val state = _pageData.value
        if (state is RequestResultState.Success) {
            savePageDataToStorage(state.data)
        }
    }

    fun onUrlChanged(url: String) {
        _url.value = url
    }

    fun onUrlInputCompleted() {
        fetchPageData(_url.value)
    }

    private fun savePageDataToStorage(pageData: PageData) {
        viewModelScope.launch(Dispatchers.IO) {
            val params = SavePageDataToStorageUseCaseParams(
                url = pageData.url,
                title = pageData.title,
                description = pageData.description,
                imageUrl = pageData.imageUrl,
            )
            savePageDataToStorageUseCase.invoke(params).collect { result ->
                _savingState.value = when (result) {
                    is UseCaseResult.Loading -> RequestResultState.loading()
                    is UseCaseResult.Success -> RequestResultState.success(result.data)
                    is UseCaseResult.Failure -> RequestResultState.failure(result.error)
                }
            }
        }
    }

    private fun fetchPageData(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val params = FetchPageDataByLinkUseCaseParams(url)
            fetchPageDataByLinkUseCase.invoke(params).collect { result ->
                _pageData.value = when (result) {
                    is UseCaseResult.Loading -> RequestResultState.loading()
                    is UseCaseResult.Success -> RequestResultState.success(result.data)
                    is UseCaseResult.Failure -> RequestResultState.failure(result.error)
                }
            }
        }
    }

}