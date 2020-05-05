package happy.family.android.forecastapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import happy.family.android.forecastapp.data.WeatherstackApiService
import happy.family.android.forecastapp.data.network.response.CurrentWeatherResponse
import happy.family.android.forecastapp.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherstackApiService: WeatherstackApiService
) : WeatherNetworkDataSource {


    private val _downLoadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downLoadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downLoadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
       try {
           val fetchCurrentWeather = weatherstackApiService
               .getCurrentWeather(location)
               .await()
           _downLoadedCurrentWeather.postValue(fetchCurrentWeather)
       }
       catch (e: NoConnectivityException){
           Log.e("Connectivity", "No internet Connection.", e)
       }
    }
}