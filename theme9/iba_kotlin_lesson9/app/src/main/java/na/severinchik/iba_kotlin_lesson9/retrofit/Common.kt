package na.severinchik.iba_kotlin_lesson9.retrofit


object Common {
    private val BASE_URL = "https://www.metaweather.com/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}