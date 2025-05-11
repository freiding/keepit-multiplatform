package by.freiding.keepit.ui.screen.links.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.freiding.keepit.domain.model.StoredLinkData
import by.freiding.keepit.domain.usecase.links.FetchStoredLinksListUseCase
import by.freiding.keepit.domain.usecase.links.FetchStoredLinksListUseCaseParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoredLinksListScreenViewModel(
    private val fetchStoredLinksListUseCase: FetchStoredLinksListUseCase
): ViewModel() {

    private val _links: MutableStateFlow<List<StoredLinkData>> = MutableStateFlow(emptyList())
    val links: StateFlow<List<StoredLinkData>> = _links.asStateFlow()

    init {
        viewModelScope.launch {
            val params = FetchStoredLinksListUseCaseParams(
                isRead = false
            )
            fetchStoredLinksListUseCase.invoke(params).collect { list ->
                _links.value = list
            }
        }
    }
}