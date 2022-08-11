package com.jun.template.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jun.common.base.BaseViewModel
import com.jun.template.data.dto.Resource
import com.jun.template.data.repositorysource.TempRepository
import com.jun.template.data.vo.Temp
import kotlinx.coroutines.flow.collect
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