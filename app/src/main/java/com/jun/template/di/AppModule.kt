package com.jun.template.di

import com.jun.common.Constants
import com.jun.common.net.NetworkHandler
import com.jun.common.utils.L
import com.jun.model.local.AppDatabase
import com.jun.model.local.TempLocalData
import com.jun.model.remote.TempRemoteData
import com.jun.model.remote.api.TempService
import com.jun.model.repositorysource.TempRepository
import com.jun.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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
        OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor { chain -> // 拿到我们的请求
                val original = chain.request()
                // 重新进行build
                val builder: Request.Builder = original.newBuilder()
                //  builder.addHeader("Content-Type", "application/json")
                val newRequest: Request = builder.build()
                L.d("http ${newRequest.url}")
                // 返回
                chain.proceed(newRequest)
            })
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
    single { MainViewModel(get())}
}

val allModules = appModule + dataModule + viewModelModule