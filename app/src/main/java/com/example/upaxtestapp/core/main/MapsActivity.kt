package com.example.upaxtestapp.core.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.upaxtestapp.R
import com.example.upaxtestapp.api.Resource
import com.example.upaxtestapp.api.Status
import com.example.upaxtestapp.common.utils.Utils
import com.example.upaxtestapp.core.base.BaseActivity
import com.example.upaxtestapp.core.main.viewmodel.UpaxViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : BaseActivity(), OnMapReadyCallback {

    private val mViewModel: UpaxViewModel by lazy {
        ViewModelProvider(this).get(UpaxViewModel::class.java)
    }

    private val mPathObserver = Observer<Resource<String>> {
        when (it.status) {
            Status.SUCCESS -> {
                hideProgressLoader()
            }
            Status.ERROR -> {
                hideProgressLoader()
            }
            Status.LOADING -> showProgressLoader()
        }
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        retrieveResponseServices()
        btn_button.setOnClickListener {
            v -> markInfo()
        }
    }

    private fun retrieveResponseServices() {
        mViewModel.mPath.observe(this, mPathObserver)
    }

    private fun markInfo() {
      val size = txt_data.editText.text.toString().toInt()
        repeat((1..size).count()) {
            val lat = Utils.getLatLog()
            val lon = Utils.getLatLog()
            val randomMarker = LatLng(lat, lon)
            mMap.addMarker(MarkerOptions().position(randomMarker).title("Random Marker"))
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(19.45, -99.12)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Mexico city"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
