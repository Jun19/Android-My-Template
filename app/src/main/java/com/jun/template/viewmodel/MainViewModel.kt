package com.jun.template.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jun.template.common.base.BaseViewModel
import com.jun.template.common.model.Resource
import com.jun.template.data.entity.Temp
import com.jun.template.data.repositorysource.TempRepository
import kotlinx.coroutines.launch

/**
 * Main ViewModel
 *
 * @author Jun
 * @time 2022/2/18
 */
class MainViewModel(private val tempRepository: TempRepository) : BaseViewModel() {
    var tempLocal: MutableLiveData<Resource<Temp>> = MutableLiveData()
    var tempRemote: MutableLiveData<Resource<Temp>> = MutableLiveData()

    fun getTempData() {
        viewModelScope.launch {
            tempLocal.value = Resource.Loading()
            tempRepository.getTempData().collect {
                tempLocal.value = it
            }
        }
    }

    fun fetchTempData() {
        viewModelScope.launch {
            tempRemote.value = Resource.Loading()
            tempRepository.fetchTempData().collect {
                tempRemote.value = it
            }
        }
    }
}