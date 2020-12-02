package na.severinchik.iba_kotlin_lesson9

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import na.severinchik.iba_kotlin_lesson9.databinding.ActivityMainBinding
import na.severinchik.iba_kotlin_lesson9.retrofit.Common
import na.severinchik.iba_kotlin_lesson9.retrofit.RetrofitServices
import retrofit2.Callback
import retrofit2.Response
import kotlin.time.measureTime


class MainActivity : AppCompatActivity() {
    var mService: RetrofitServices = Common.retrofitService
    val cities = listOf("Minsk", "Moscow", "London", "Barcelona", "Kiev", "Hong Kong")
    var i = 0

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)
        refresh()
/*
        Common.retrofitService.getMainUICity(cities[i]).enqueue(object : Callback<List<Parent>> {
            override fun onResponse(call: Call<List<Parent>>, response: Response<List<Parent>>) {
                    response.body()
            }

            override fun onFailure(call: Call<List<Parent>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })*/

//        newSingleThreadContext("Ctx1").use { ctx1 ->
//            newSingleThreadContext("Ctx2").use { ctx2 ->
//                runBlocking(ctx1) {
//                    Log.d( "TAG","Started in ctx1")
//                    withContext(ctx2) {
//                        Log.d("TAG","Working in ctx2",)
//                    }
//                    Log.d("TAG","Back to ctx1",)
//                }
//            }
//        }

        binding.refresh.setOnClickListener {
            if (i < cities.size - 1) {
                i++
            } else {
                i = 0
            }
            refresh();

        }
    }

    fun refresh() {
        val city = viewModel.getCities(cities[i]).first()
        val weather = viewModel.getWaether(city.woeid.toLong())
        val temp = weather.consolidated_weather.first().the_temp
        binding.temperature.text = temp.toString().substringBefore(".") + " C"
        binding.city.text = city.title
        setStateWeather(
            weather.consolidated_weather.first().weather_state_abbr,
            binding.weatherState,
            this
        )
//        with()
        val somefun = sample { i: Int, i1: Int -> Log.d("TAG", "messsa ${i} ${i1}") }

    }


}

inline fun sample(body: (Int, Int) -> Unit) {}

fun setStateWeather(weatherState: String, imageView: ImageView, context: Context) {
    GlideToVectorYou
        .init()
        .with(context)
        .setPlaceHolder(
            R.drawable.ic_baseline_broken_image_24,
            R.drawable.ic_baseline_broken_image_24
        )
        .load(
            Uri.parse("https://www.metaweather.com/static/img/weather/${weatherState}.svg"),
            imageView
        );

//    Picasso.get().load("https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg").into(imageView)
}
