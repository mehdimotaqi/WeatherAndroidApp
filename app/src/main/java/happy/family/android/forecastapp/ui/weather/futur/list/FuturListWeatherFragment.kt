package happy.family.android.forecastapp.ui.weather.futur.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import happy.family.android.forecastapp.R

class FuturListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            FuturListWeatherFragment()
    }

    private lateinit var viewModel: FuturListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.futur_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FuturListWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
