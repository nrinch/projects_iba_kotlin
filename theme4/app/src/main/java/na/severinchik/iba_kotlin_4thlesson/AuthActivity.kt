package na.severinchik.iba_kotlin_4thlesson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import na.severinchik.iba_kotlin_4thlesson.databinding.ActivityAuthBinding
import soup.neumorphism.NeumorphImageView

class AuthActivity : AppCompatActivity() {
    private var expectedPin = "1234"
    private var pin: String = "";
    private lateinit var pins: Array<NeumorphImageView>;

    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pins = arrayOf(binding.pinFirst, binding.pinTwo, binding.pinThree, binding.pinFour)
        binding.clear.setOnClickListener { clear() }


    }

    fun enterNumber(v: View) {
        var button = v as Button
        binding.wrongPinText.visibility = View.INVISIBLE
        if (pin.length < 4) {
            pin += button.text
            pins[pin.length - 1].setBackgroundColor(getColor(R.color.colorSunday))
        }

        if (expectedPin.equals(pin)) {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

            )
            finish()
        } else {
            if (pin.length == 4) binding.wrongPinText.visibility = View.VISIBLE
        }
    }

    private fun clear() {
        if (!pin.isEmpty()) {
            pins[pin.length - 1].setBackgroundColor(getColor(R.color.colorOnyx))
            pin = pin.substring(0, pin.length - 1);
        }
    }
}