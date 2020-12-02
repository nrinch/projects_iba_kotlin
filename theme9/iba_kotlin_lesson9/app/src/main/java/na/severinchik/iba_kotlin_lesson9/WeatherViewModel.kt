package na.severinchik.iba_kotlin_lesson9

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import na.severinchik.iba_kotlin_lesson9.retrofit.Common
import na.severinchik.iba_kotlin_lesson9.retrofit.RetrofitClient
import na.severinchik.iba_kotlin_lesson9.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var context:Context
    init {
        context = application.applicationContext
    }

    fun getWaether(woeid: Long): Weather {
        var weather: Weather
        runBlocking {
            val result = async { Common.retrofitService.getWeather(woeid) }
//            runBlocking {
            weather = result.await()
simple().catch {  }
//            }
        }
        return weather
    }



    fun simple(): Flow<Int> = flow {
        emit(1)
        throw RuntimeException()
    }

    fun getCities(name: String): List<Parent> {
        var list: ArrayList<Parent> = ArrayList()
        repeat(10) {
            Log.d("Cor", "getCities: ")
            runBlocking {
                val result = async {
                    Common.retrofitService.getCity(name)

                }
//            runBlocking {
                list = result.await() as ArrayList<Parent>
//            }
            }
        }
        return list
    }

}