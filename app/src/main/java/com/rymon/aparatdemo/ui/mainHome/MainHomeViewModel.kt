package com.rymon.aparatdemo.ui.mainHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rymon.aparatdemo.data.AparatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val repository: AparatRepository

) : ViewModel() {

    val videos = repository.getMainHomeData().cachedIn(viewModelScope)

}