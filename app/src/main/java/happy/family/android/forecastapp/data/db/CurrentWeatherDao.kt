package happy.family.android.forecastapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import happy.family.android.forecastapp.data.db.entity.CURRENT_WEATHER_ID
import happy.family.android.forecastapp.data.db.entity.CurrentWeatherEntry


@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<CurrentWeatherEntry>
}