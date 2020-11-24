package na.severinchik.iba_kotlin_lesson7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import java.util.EnumSet.range

class MainActivity : AppCompatActivity() {
    var string:String = ""
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_menu_about -> {
                Toast.makeText(this, "Lesson 7", Toast.LENGTH_LONG).show()
                true
            }
            R.id.subitem1 -> {
                navController.navigate(R.id.action_global_dialogsFragment)
                true
            }
            R.id.subitem2 -> {
                navController.navigate(R.id.action_global_itemFragment)
                true
            }
            R.id.subitem3 ->{
                navController.navigate(R.id.action_global_JSOnFragment)
                true
            }
            R.id.menu_group_red, R.id.menu_group_green -> {
                item.isChecked = !item.isChecked
                Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}