package happy.family.android.forecastapp.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import happy.family.android.forecastapp.data.network.ConnectivityIntercepter
import happy.family.android.forecastapp.data.network.ConnectivityIntercepterImpl
import happy.family.android.forecastapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "b4a07f84d28ecee94d38b7510e08128a"
const val BASE_URL = "http://api.weatherstack.com/"

// http://api.weatherstack.com/current?access_key=b4a07f84d28ecee94d38b7510e08128a&query=New%20York

interface WeatherstackApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
    ): Deferred<CurrentWeatherResponse>


    companion object{
        operator fun invoke(
            connectivityIntercepter: ConnectivityIntercepter
        ): WeatherstackApiService{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                 return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityIntercepter)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherstackApiService::class.java)
        }
    }
}