package com.aru.sd.assessment

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.LocationSource
import com.hp.hpl.jena.ontology.OntModel

class MyApplication : Application(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationSource
{
    lateinit var mainModel: OntModel
    private lateinit var sInstance: MyApplication
    private lateinit var mLocation: Location

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationCallback: LocationCallback? = null

    lateinit var mGoogleApiClient: GoogleApiClient
    private var mLocationRequest: LocationRequest? = null

    private var mListener: LocationSource.OnLocationChangedListener? = null

    override fun onCreate() {
        super.onCreate()

        sInstance = this
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        mLocation = Location("")
        //mLocation.latitude = 41.656554
        //mLocation.latitude = 43.136550

        mGoogleApiClient.connect()
    }

    fun getInstance(): MyApplication {
        return sInstance
    }

    fun get(context: Context): MyApplication {
        return context.applicationContext as MyApplication
    }

    override fun onConnected(connectionHint: Bundle?) {}

    fun getLocation(): Location? {
        return if (mLocation == null)
            null
        else
            mLocation
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}

    override fun onConnectionSuspended(i: Int) {}

    fun updateUserLocation(location: Location?) {
        if (location == null)
            return

        if (location.longitude == 0.0 && location.latitude == 0.0)
            return

        if (mListener != null) {
            if (location != null)
                mListener!!.onLocationChanged(location)
        }
    }

    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
        mListener = onLocationChangedListener
        if (mLocation != null)
            onLocationChangedListener.onLocationChanged(mLocation)
    }

    override fun deactivate() {}

    fun disconnectGoogleApi() {
        try {
            //removeLocationUpdates()
            if (mGoogleApiClient.isConnected)
                mGoogleApiClient.disconnect()
        } catch (e: Exception) {
        }

    }

    fun addLocationUpdates() {
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            mFusedLocationClient!!.lastLocation.addOnSuccessListener {

                if (it != null) {
                    mLocation = it
                }
            }

            mLocation = Location("")
            mLocation.setLatitude(33.07576972733938)
            mLocation.setLongitude(-96.79364512950025)

            mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(4 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(2 * 1000)
                .setSmallestDisplacement(2f) // 1 second, in milliseconds

            mLocationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    if (locationResult == null) {
                        return
                    }

                    for (location in locationResult!!.getLocations()) {
                        if (location.getLatitude() != 0.0) {

                            mLocation = location
                            updateUserLocation(mLocation)
                        }
                    }
                }
            }

            mFusedLocationClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
        } catch (e: Exception) {
            try {
                mGoogleApiClient.connect()
            } catch (ex: Exception) {
            }

        }
    }

    fun setLocation(location: Location) {
        mLocation = location
    }

    fun getLatitude(): Double {
        return if (mLocation == null)
            0.0
        else
            mLocation.latitude
    }

    fun getLongitude(): Double {
        return if (mLocation == null)
            0.0
        else
            mLocation.longitude
    }
}