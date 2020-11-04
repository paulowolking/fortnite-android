package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wolking.fortnite.data.AppRepository
import com.wolking.fortnite.data.model.Response
import com.wolking.fortnite.data.model.Stats
import com.wolking.fortnite.presentation.Resource

class HomeViewModel(
    private val appRepository: AppRepository
) : DisposingViewModel() {

    var stats = MutableLiveData<Resource<Response<Stats>>>()

    fun getStats(refresh: Boolean = false, name: String) {

        stats.value = Resource.Loading()

        addDisposable(appRepository.getStats(name = name).subscribe(
            { data ->
                stats.value = Resource.Success(data)
            },
            { error ->
                stats.value = Resource.Failure(error)
            }
        ))

    }

}