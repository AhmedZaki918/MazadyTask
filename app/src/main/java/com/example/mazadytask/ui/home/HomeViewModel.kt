package com.example.mazadytask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazadytask.data.model.categories.CategoriesResponse
import com.example.mazadytask.data.model.properties.PropertiesResponse
import com.example.mazadytask.data.network.Resource
import com.example.mazadytask.data.repository.CategoriesRepo
import com.example.mazadytask.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeUi(
    val categories: CategoriesResponse = CategoriesResponse(),
    val properties: PropertiesResponse = PropertiesResponse(),
    val homeState: RequestState = RequestState.IDLE,
    val propertiesState: RequestState = RequestState.IDLE,
    val errorMessage: String = "",
    val defaultCategoryName: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: CategoriesRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUi())
    val uiState: StateFlow<HomeUi> = _uiState.asStateFlow()

    init {
        fetchAllCategories()
    }

    private fun fetchAllCategories() {
        viewModelScope.launch {
            val result = repo.allCategories()

            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(
                        categories = result.data,
                        homeState = RequestState.SUCCESS
                    )
                }
                val subCategoryId = uiState.value.categories.data.categories[0].children[0].id
                fetchProperties(subCategoryId)

            } else if (result is Resource.Failure) {
                _uiState.update {
                    it.copy(
                        errorMessage = it.errorMessage,
                        homeState = RequestState.ERROR
                    )
                }
            }
        }
    }

    fun fetchProperties(categoryId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    propertiesState = RequestState.LOADING,
                    defaultCategoryName = false
                )
            }
            val result = repo.properties(categoryId)

            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(
                        properties = result.data,
                        propertiesState = RequestState.SUCCESS
                    )
                }
            } else if (result is Resource.Failure) {
                _uiState.update {
                    it.copy(
                        errorMessage = it.errorMessage,
                        propertiesState = RequestState.ERROR
                    )
                }
            }
        }
    }
}