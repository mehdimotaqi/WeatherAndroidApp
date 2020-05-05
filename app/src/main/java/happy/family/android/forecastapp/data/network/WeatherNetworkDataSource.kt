package happy.family.android.forecastapp.data.network

import androidx.lifecycle.LiveData
import happy.family.android.forecastapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val downLoadedCurrentWeather: LiveData<CurrentWeatherResponse>


    suspend fun fetchCurrentWeather(
        location: String
    )
}