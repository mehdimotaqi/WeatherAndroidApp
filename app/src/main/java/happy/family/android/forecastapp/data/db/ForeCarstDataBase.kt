package happy.family.android.forecastapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import happy.family.android.forecastapp.data.db.entity.CurrentWeatherEntry


@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)

abstract class ForeCarstDataBase: RoomDatabase(){

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instance: ForeCarstDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDataBase(context).also { instance = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForeCarstDataBase::class.java, "forecast.db")
                .build()
    }
}