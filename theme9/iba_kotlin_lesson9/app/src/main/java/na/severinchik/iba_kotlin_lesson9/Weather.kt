package na.severinchik.iba_kotlin_lesson9

data class Weather(
    val consolidated_weather: List<ConsolidatedWeather>,
    val latt_long: String,
    val location_type: String,
    val parent: Parent,
    val sources: List<Source>,
    val sun_rise: String,
    val sun_set: String,
    val time: String,
    val timezone: String,
    val timezone_name: String,
    val title: String,
    val woeid: Int
)

data class ConsolidatedWeather(
    val air_pressure: Double,
    val applicable_date: String,
    val created: String,
    val humidity: Int,
    val id: Long,
    val max_temp: Double,
    val min_temp: Double,
    val predictability: Int,
    val the_temp: Double,
    val visibility: Double,
    val weather_state_abbr: String,
    val weather_state_name: String,
    val wind_direction: Double,
    val wind_direction_compass: String,
    val wind_speed: Double
)

data class Parent(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)

data class Source(
    val crawl_rate: Int,
    val slug: String,
    val title: String,
    val url: String
)