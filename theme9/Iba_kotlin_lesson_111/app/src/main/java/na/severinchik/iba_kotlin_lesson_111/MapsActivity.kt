package na.severinchik.iba_kotlin_lesson_111


import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

const val TAG: String = "TAG"

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {
    lateinit var mService: SampleService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SampleService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

    }

    val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10.toLong()
    val MIN_TIME_BW_UPDATES: Float = (1000 * 60 * 1).toFloat()
    private lateinit var mMap: GoogleMap
    private val PERMISSION_CODE = 1001
    var permissionGranted = PackageManager.PERMISSION_GRANTED
    lateinit var mapFragment: SupportMapFragment
    lateinit var locationManager: LocationManager
    private val locationListener = LocationListener {

        val curPos = LatLng(it.latitude, it.longitude)

        mMap.addMarker(MarkerOptions().draggable(true).position(curPos).title("Current Position"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curPos))
    }
    var isGPSEnabled = false
    var isNetworkEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

//        Intent(this, SampleService::class.java).also { intent -> startService(intent) }

//        val myServiceIntent = Intent(this, ForegroundService::class.java)
//        myServiceIntent.putExtra(inputExtra, "Work")
//        ContextCompat.startForegroundService(this, myServiceIntent)

    }

    override fun onStart() {
        super.onStart()

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != permissionGranted &&
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != permissionGranted
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                PERMISSION_CODE
            )
        }

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                MIN_TIME_BW_UPDATES,
                locationListener
            )
        }
        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                MIN_TIME_BW_UPDATES,
                locationListener
            )
        }
//        Log.d(TAG, "onStart: $isNetworkEnabled $isGPSEnabled")

//        Intent(this, SampleService::class.java).also { intent ->
//            bindService(intent, connection, Context.BIND_AUTO_CREATE)
//        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMarkerClickListener(this)

//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().draggable(true).position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == permissionGranted) {
            } else {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                ) {
                    AlertDialog.Builder(this)
                        .setMessage("You have denied permanently these permissions, please go to setting to enable these permissions.")
                        .setCancelable(true)
                        .setPositiveButton("Go to Settings") { dialogInterface, i -> goToApplicationSettings() }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            }
        }
    }

    private fun goToApplicationSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
//        unbindService(connection)
//        mBound = false
//        val serviceIntent = Intent(this, ForegroundService::class.java)
//        stopService(serviceIntent)
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        Log.d(TAG, "onMarkerClick: ${marker.position.latitude}")
        mMap.addMarker(MarkerOptions().position(marker.position).title("Marker"))


        return false
    }


}