package com.jun.template.di

import com.jun.template.common.Constants
import com.jun.template.common.net.NetworkHandler
import com.jun.template.common.utils.L
import com.jun.template.data.local.AppDatabase
import com.jun.template.data.local.TempLocalData
import com.jun.template.data.remote.TempRemoteData
import com.jun.template.data.remote.api.TempService
import com.jun.template.data.repositorysource.TempRepository
import com.jun.template.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 注入
 *
 * @author Jun
 * @time 2022/2/18
 */
val appModule = module {
    single {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message: String -> L.d("http log: $message") }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
val dataModule = module {
    //tempData
    single { TempService(get()) }
    single { NetworkHandler(get()) }
    single { TempRemoteData(get(), get()) }
    single { TempLocalData(AppDatabase.getDBInstance(get()).getTempDataDao()) }
    single { TempRepository(get(), get(), Dispatchers.IO) }

}
val viewModelModule = module {
    //viewmodel
    factory { MainViewModel(get()) }
}

val allModules = appModule + dataModule + viewModelModule