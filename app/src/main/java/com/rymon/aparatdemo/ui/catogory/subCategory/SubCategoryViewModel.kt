package com.rymon.aparatdemo.ui.catogory.subCategory

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rymon.aparatdemo.data.AparatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubCategoryViewModel @Inject constructor(
    private val repository: AparatRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentCategoryId = state.getLiveData(CURRENT_CATEGORY_ID, DEFAULT_CATEGORY_ID)

    val categoryVideos = currentCategoryId.switchMap { categoryId ->
        repository.getSubCategoryResults(categoryId).cachedIn(viewModelScope)
    }

    fun searchVideos(categoryId: Int) {
        currentCategoryId.value = categoryId
    }

    companion object {
        private const val CURRENT_CATEGORY_ID = "current_category_id"
        private const val DEFAULT_CATEGORY_ID = 1
    }
}