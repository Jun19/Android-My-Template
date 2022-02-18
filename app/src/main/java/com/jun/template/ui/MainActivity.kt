package com.jun.template.ui

import com.jun.common.base.BaseBindingActivity
import com.jun.common.extension.observe
import com.jun.common.utils.UpdateUtil
import com.jun.model.dto.Resource
import com.jun.model.vo.Temp
import com.jun.template.databinding.ActivityMainBinding
import com.jun.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init() {
        UpdateUtil.startFlexibleUpdate(this)
        observe(mainViewModel.tempLocal, ::handleTempLocal)
        observe(mainViewModel.tempRemote, ::handleTempRemote)

        mBinding.tvRemote.postDelayed({
            mainViewModel.getTempData()
            mainViewModel.fetchTempData()
        }, 1500)

    }

    private fun handleTempLocal(resource: Resource<Temp>) {
        when (resource) {
            is Resource.Loading -> {
                mBinding.tvLocal.text = "loading..."
            }
            is Resource.Success -> {
                mBinding.tvLocal.text = resource.data.toString()
            }
            is Resource.Failure -> {
                mBinding.tvLocal.text = resource.dataException.toString()
            }
        }
    }

    private fun handleTempRemote(resource: Resource<Temp>) {
        when (resource) {
            is Resource.Loading -> {
                mBinding.tvRemote.text = "loading..."
            }
            is Resource.Success -> {
                mBinding.tvRemote.text = resource.data.toString()
            }
            is Resource.Failure -> {
                mBinding.tvRemote.text = resource.dataException.toString()
            }
        }
    }
}