package happy.family.android.forecastapp.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
    @SerializedName("observation_time")
    val observationTime: String,
    val temperature: Double,
    @SerializedName("weather_code")
    val weatherCode: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_degree")
    val windDegree: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    val pressure: Double,
    val precip: Double,
    val humidity: Double,
    val cloudcover: Double,
    val feelslike: Double,
    @SerializedName("uv_index")
    val uvIndex: Double,
    val visibility: Double,
    @SerializedName("is_day")
    val isDay: String
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_WEATHER_ID
}