package com.rymon.aparatdemo.ui.catogory.mainCategory

import androidx.hilt.Assisted
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.rymon.aparatdemo.data.AparatRepository
import com.rymon.aparatdemo.data.category.Category
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val repository: AparatRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Category>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllCategories() {
        job = viewModelScope.launch {
            val response = repository.getAllCategory()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.categories)
                    loading.value = false
                } else {
                    exceptionHandler
//                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}