package happy.family.android.forecastapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException

class ConnectivityIntercepterImpl(
    context: Context
) : ConnectivityIntercepter {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isOnline())
            throw ConnectException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
       return networkInfo != null && networkInfo.isConnected
    }
}