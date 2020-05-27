package com.example.upaxtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btn_button.setOnClickListener {
            v -> //markInfo()
        }
    }

    private fun markInfo() {
   /*     val size = txt_data.text.toString().toInt()
        repeat((1..size).count()) {
            val lat = Utils.getLatLog()
            val lon = Utils.getLatLog()
            val randomMarker = LatLng(lat, lon)
            mMap.addMarker(MarkerOptions().position(randomMarker).title("Random Marker"))
        }*/

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(19.45, -99.12)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Mexico city"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
