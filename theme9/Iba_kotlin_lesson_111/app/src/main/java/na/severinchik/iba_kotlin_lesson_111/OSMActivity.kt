package na.severinchik.iba_kotlin_lesson_111

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import na.severinchik.iba_kotlin_lesson_111.databinding.ActivityOSMBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.io.File


class OSMActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE: Int = 1
    lateinit var binding: ActivityOSMBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_o_s_m)
        val ctx: Context = applicationContext

        Configuration.getInstance().load(ctx, getPreferences(Context.MODE_PRIVATE))
        binding.osmMap.setTileSource(TileSourceFactory.MAPNIK)
        var mapController = binding.osmMap.controller
        mapController.setZoom(8.9)
        var overlay = MyLocationNewOverlay(binding.osmMap)
        overlay.isEnabled = true
        binding.osmMap.overlays.add(overlay)
        mapController.setCenter(GeoPoint(60.6639, 15.5474))
        requestPermissionsIfNecessary(
            arrayOf(
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

    }

    override fun onResume() {
        super.onResume()
        binding.osmMap.onResume()
    }


    override fun onPause() {
        super.onPause()
        binding.osmMap.onPause()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        val permissionsToRequest: ArrayList<String?> = ArrayList()
        for (i in grantResults.indices) {
            permissionsToRequest.add(permissions[i])
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toArray(arrayOfNulls(0)),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }


    private fun requestPermissionsIfNecessary(permissions: Array<String>) {
        val permissionsToRequest: ArrayList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toArray(arrayOfNulls(0)),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }
}