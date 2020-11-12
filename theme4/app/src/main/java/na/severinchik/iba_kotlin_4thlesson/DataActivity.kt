package na.severinchik.iba_kotlin_4thlesson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import na.severinchik.iba_kotlin_4thlesson.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.user = intent.getParcelableExtra(MainActivity.PRO_USER)

    }

    fun back(v: View) {
        val data = Intent().apply { putExtra(MainActivity.PRO_USER,binding.user) }
        setResult(RESULT_OK, data)
        finish()
    }
}