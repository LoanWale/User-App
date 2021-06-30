package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

class CurrentRecoveryAddressActivity : AppCompatActivity() {
     var binding :ActivityCurrentRecoveryAddressBinding? = null
    lateinit var startVisitViewModel : StartVisitViewModel
    private val LOCATION_CODE = 1
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)





        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        binding!!.backLayout.setOnClickListener {
            finish()
        }
        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
           starttnow.setBackgroundResource(R.color.applColor)

        }
        binding!!.starttnow.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            init()
            verifyClick()
            getLastLocation()
            getCurrentDate()
        }

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
        if (lead_id!= null && company_id!= null && user_id!=null && product_id != null && executive_start_letitude != null && executive_start_longitude != null && followup_satarted_at != null) {
            val body = StartVisitRequestBodies.StartVisitRequest(
                lead_id.toInt(),
                company_id.toString(),
                user_id.toString(),
                product_id.toString(),
                executive_start_letitude,
                executive_start_longitude.toInt(),
                followup_satarted_at
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

    private fun getLastLocation(){
        if (checkLocationPermission()){

            if (isLocationEnabled()){

                fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task ->
                    var loaction : Location? = task.result
                    if (loaction==null){
                        getNullLocation()
                    }else{
                        location_id.text =" "+ loaction.latitude+" "+ loaction.longitude+
                                "\n your area "+getCityName(loaction.latitude,loaction.longitude)+
                                "\n your city "+ getCountryName(loaction.latitude,loaction.longitude)

                    }
                }

            }else{
                Toast.makeText(this,"Location not on",Toast.LENGTH_LONG).show()
            }

        }else{
            RequestLocationPermission()
        }
    }
    private fun checkLocationPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun RequestLocationPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),LOCATION_CODE)
    }

    private fun isLocationEnabled():Boolean{
        var locationManger: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManger.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun getNullLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2

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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback
        ,Looper.myLooper())

    }
    private val locationCallback = object :LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation : Location = p0.lastLocation

            location_id.text =" "+ lastLocation.latitude+" "+ lastLocation.longitude
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }

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
    private fun getCurrentDate(){


        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        dateFormatter.setLenient(false)
        val today = Date()
        val s: String = dateFormatter.format(today)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        calender_id.text = s

       /* val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            calender_id.setText("" + dayOfMonth + " " + MONTHS + ", " + year)

        }, year, month, day)

        dpd.show()*/

    }
}