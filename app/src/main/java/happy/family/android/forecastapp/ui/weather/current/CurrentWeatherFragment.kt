package happy.family.android.forecastapp.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import happy.family.android.forecastapp.R
import happy.family.android.forecastapp.data.WeatherstackApiService
import happy.family.android.forecastapp.data.network.ConnectivityIntercepterImpl
import happy.family.android.forecastapp.data.network.WeatherNetworkDataSource
import happy.family.android.forecastapp.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)

        val apiService = WeatherstackApiService(ConnectivityIntercepterImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downLoadedCurrentWeather.observe(this, Observer {
            textView.text = it.currentWeatherEntry.windSpeed.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            textView.text = "Please Wait ..."
            weatherNetworkDataSource.fetchCurrentWeather("Casablanca")

        }
    }

}
