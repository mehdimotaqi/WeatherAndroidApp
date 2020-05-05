package happy.family.android.forecastapp.data.network.response

import com.google.gson.annotations.SerializedName
import happy.family.android.forecastapp.data.db.entity.CurrentWeatherEntry
import happy.family.android.forecastapp.data.db.entity.Location
import happy.family.android.forecastapp.data.db.entity.Request


data class CurrentWeatherResponse(
    val request: Request,
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)