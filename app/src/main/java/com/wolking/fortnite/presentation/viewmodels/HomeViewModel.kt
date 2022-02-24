package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val statsRepository: StatsRepository
) : DisposingViewModel() {

    var stats = MutableLiveData<Resource<Response<Stats>>>()

    fun getStats(refresh: Boolean = false, name: String) {

        stats.value = Resource.Loading()

        addDisposable(statsRepository.getStats(name = name).subscribe(
            { data ->
                stats.value = Resource.Success(data)
            },
            { error ->
                stats.value = Resource.Failure(error)
            }
        ))

    }

}