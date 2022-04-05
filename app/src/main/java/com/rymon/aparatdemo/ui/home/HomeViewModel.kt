package com.rymon.aparatdemo.ui.home

import androidx.hilt.Assisted
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.rymon.aparatdemo.data.AparatRepository
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AparatRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    val videos = repository.getHomeResult().cachedIn(viewModelScope)

    val videosWithoutPaging = liveData(Dispatchers.IO) {
        val retrivedVideosWithoutPaging = repository.getHomeResultWithoutPaging()
        emit(retrivedVideosWithoutPaging)
    }

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<HomeVideoIncluded>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllVideoIncludedWithoutPaging() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHomeResultWithoutPaging()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.included)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
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