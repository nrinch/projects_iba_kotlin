package na.severinchik.iba_kotlin_scnd_lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException
import java.lang.reflect.Array
import javax.security.auth.Subject

class MainActivity : AppCompatActivity() {

    var firstArg: String = ""
    var secondArg: String = ""
    var operation: String = ""
    var isSecondArg:Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun numberInput(v: View) {
        var button: Button = v as Button
        if(isSecondArg){
            secondArg+=button.text
            output_text_view.text = secondArg

        }else {
            firstArg += button.text
            output_text_view.text = firstArg
        }

    }

    fun operationInput(v: View) {
        var button: Button = v as Button
        operation = button.text.toString()
        isSecondArg = true
        logs_text_view.text = firstArg
        output_text_view.text = ""

    }

    fun clear(v:View){
        output_text_view.text = ""
        firstArg = ""
        secondArg = ""
        isSecondArg =false
        operation = ""

    }

    fun equal(v: View) {
      var result =  when(operation){
            "/"-> firstArg.toInt()/secondArg.toInt()
            "*"-> firstArg.toInt()*secondArg.toInt()
            "+"-> firstArg.toInt()+secondArg.toInt()
            "-"-> firstArg.toInt()-secondArg.toInt()
            else -> Toast.makeText(this,"Alarm",Toast.LENGTH_LONG).show()
        }

        logs_text_view.text = "${firstArg} ${operation} ${secondArg}="
        output_text_view.text = result.toString()
        isSecondArg = false
    }


}