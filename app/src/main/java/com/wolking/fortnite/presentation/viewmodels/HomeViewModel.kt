package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val statsRepository: StatsRepository
) : ViewModel() {

    var stats: MutableLiveData<Resource<NetworkResult<Stats>>> = MutableLiveData()

    fun getStats(name: String) = CoroutineScope(Dispatchers.Main).launch {
        stats.value = withContext(Dispatchers.Default) {
            statsRepository.getStats(name = name)
        }
    }

}