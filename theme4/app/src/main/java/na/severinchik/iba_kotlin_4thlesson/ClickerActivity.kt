package na.severinchik.iba_kotlin_4thlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import na.severinchik.iba_kotlin_4thlesson.databinding.ActivityClickerBinding
import na.severinchik.iba_kotlin_4thlesson.databinding.ActivityMainBinding

class ClickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClickerBinding

    var counter: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClickerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.count = counter.toString()
        binding.incriment.setOnClickListener {
            counter++
            binding.count = counter.toString()
        }
        binding.decrement.setOnClickListener {
            counter--
            binding.count = counter.toString()
        }
    }
}