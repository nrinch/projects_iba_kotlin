package na.severinchik.iba_kotlin_4thlesson

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import na.severinchik.iba_kotlin_4thlesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  val phone = "375291234567"

    companion object {
        private const val REQUEST_DATA = 42;
        const val PRO_USER = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        binding.user = ProUser("John", "Doe",42)

        binding.buttonPhone.setOnClickListener { callPhone() }
        binding.buttonSms.setOnClickListener { sendSMS() }
        binding.buttonEmail.setOnClickListener { sendEmail() }
        binding.buttonWeb.setOnClickListener { openLink() }
        binding.clicker.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
        binding.sendData.setOnClickListener {
            var intent = Intent(this, DataActivity::class.java)
            intent.putExtra(PRO_USER, ProUser("Jonh", "Doe", 42))
            startActivityForResult(intent, REQUEST_DATA)
        }
    }


    private fun callPhone() {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:${phone}")
        startActivity(dialIntent)
    }

    private fun sendSMS() {
        val uri = Uri.parse("smsto:${phone}")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "Hey, it's work!")
        startActivity(intent)
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("johndoe@gmai.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "I forget subject")
        intent.putExtra(Intent.EXTRA_TEXT, "bla bla bla ")
        try {
            startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun openLink() {
        val url = "http://www.google.com"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DATA) {
            data?.apply {
                var u = getParcelableExtra<ProUser>(PRO_USER)
                Toast.makeText(this@MainActivity,u.toString(),Toast.LENGTH_LONG).show()
            }
        }


    }
}


data class User(val name: String, val surname: String)