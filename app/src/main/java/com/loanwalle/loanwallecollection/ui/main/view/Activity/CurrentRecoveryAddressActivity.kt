package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.startVisit.StartVisitRequestBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityCurrentRecoveryAddressBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.StartVisitViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_current_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.startvisit
import kotlinx.android.synthetic.main.activity_resest.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class CurrentRecoveryAddressActivity : AppCompatActivity(),View.OnClickListener{
     var binding :ActivityCurrentRecoveryAddressBinding? = null
    lateinit var startVisitViewModel : StartVisitViewModel
    private val LOCATION_CODE = 1
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    private var REQUEST_LOCATION_CODE = 101
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)





        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        binding!!.backLayout.setOnClickListener {
            finish()
        }
        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
           starttnow.setBackgroundResource(R.color.applColor)

        }

        binding!!.starttnow.setOnClickListener(this)
        buildGoogleApiClient()

    }

    @Synchronized
    private fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .build()

        mGoogleApiClient!!.connect()
    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        startVisitViewModel= ViewModelProvider(this, factory).get(StartVisitViewModel::class.java)
    }
    fun verifyClick() {
        val lead_id = "2457"
        val user_id = 44
        val company_id = 2
        val product_id = 2
        val followup_satarted_at = "2021-06-24 25:45"
        val executive_start_letitude = location_id.toString()
        val executive_start_longitude = 67.8
        val loan_no = "12345"
        if (lead_id!= null && company_id!= null && user_id!=null && product_id != null && executive_start_letitude != null &&
            executive_start_longitude != null && followup_satarted_at != null && loan_no !=null) {
            val body = StartVisitRequestBodies.StartVisitRequest(
                lead_id.toInt(),
                company_id.toString(),
                user_id.toString(),
                product_id.toString(),
                executive_start_letitude,
                executive_start_longitude.toInt(),
                followup_satarted_at,
                loan_no
            )
            startVisitViewModel.startVisits(body)

            startVisitViewModel.startVisit.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()

                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success")&&verifyResponse.status.equals("200"))
                                {
                                    newprogress.errorSnack(message, Snackbar.LENGTH_LONG)
                                    val i = Intent(this@CurrentRecoveryAddressActivity, ResidanceActivity::class.java)
                                    startActivity(i)
                                    finish()

                                }
                                else

                                {
                                    newprogress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                newprogress.errorSnack(message, Snackbar.LENGTH_LONG)
                            }
                        }

                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }
            })
        }

    }

    fun hideProgressBar() {
        newprogress.visibility = View.GONE
    }
    fun showProgressBar() {
        newprogress.visibility = View.VISIBLE
    }


    override fun onClick(v: View?) {
        if (!checkGPSEnabled()) {
            return
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                getLocation();
            } else {
                //Request Location Permission
                checkLocationPermission()
            }
        } else {
            getLocation();
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
                    })
                    .create()
                    .show()

            } else ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
        }
    }

    private fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun getLocation(){

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            latitude_id.text = mLocation!!.latitude.toString()
            longitude_id.text = mLocation!!.longitude.toString()

            location_id.text =" "+ "\n your area:- "+getCityName(mLocation!!.latitude,mLocation!!.longitude)+
                    "\n your city:- "+ getCountryName(mLocation!!.latitude,mLocation!!.longitude)

            getCurrentDate()
            init()
            verifyClick()
        } else {
            Toast.makeText(this, "Location not Detected Please Wait...", Toast.LENGTH_SHORT).show();
        }
    }

    private fun getCurrentDate() {
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        dateFormatter.setLenient(false)
        val today = Date()
        val s: String = dateFormatter.format(today)
        calender_id.text = s
    }

    private fun startLocationUpdates() {
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
      //  LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)

    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }
    private fun isLocationEnabled():Boolean{
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }

    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
    }

    private fun getCityName(lat:Double,lon:Double):String{
        var cityName = ""
        var geocoder = Geocoder(this,Locale.getDefault())
        var address : List<Address> = geocoder.getFromLocation(lat,lon,1)

        cityName = address.get(0).locality
        return cityName
    }
    private fun getCountryName(lat:Double,lon:Double):String{
        var countryName = ""
        var geocoder = Geocoder(this,Locale.getDefault())
        var address : List<Address> = geocoder.getFromLocation(lat,lon,1)

        countryName = address.get(0).getAddressLine(0)
        return countryName
    }

}