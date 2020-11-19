package na.severinchik.iba_kotlin_lesson6.ui.band

data class Band(
    var id: Int,
    var name: String,
    var genre: String,
    var dataFoundation: Int,
    var from: String
)


fun bandSource(): ArrayList<Band> {
    return arrayListOf(
        Band(
            0,
            "BlackSabbath",
            "Heavy Metal",
            1968,
            "Birmingham, England"
        ),
        Band(
            1,
            "TheRollingStones",
            "Rock,Blues",
            1962,
            "London, England"
        ),
        Band(
            2,
            "TheBeatles",
            "Rock,Pop",
            1960,
            "Liverpool, England"
        ),
        Band(
            3,
            "Led Zeppelin",
            "Hard rock,Blues rock",
            1968,
            "London, England"
        ),
        Band(
            4,
            "Guns N' Roses",
            "Hard rock,Heavy Metal",
            1985,
            "Los Angeles, California, U.S."
        ),
        Band(
            5,
            "Motörhead",
            "Heavy Metal",
            1975,
            "London, England"
        )
    )
}