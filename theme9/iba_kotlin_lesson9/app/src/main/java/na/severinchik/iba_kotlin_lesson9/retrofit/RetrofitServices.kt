package na.severinchik.iba_kotlin_lesson9.retrofit

import kotlinx.coroutines.Deferred
import na.severinchik.iba_kotlin_lesson9.Parent
import na.severinchik.iba_kotlin_lesson9.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    @GET("location/{id}")
    suspend fun getWeather(@Path("id") id: Long): Weather

    @GET("location/search/")
    suspend fun getCity(@Query("query") name: String): List<Parent>

    @GET("location/search/")
    fun getMainUICity(@Query("query") name: String): Call<List<Parent>>

}