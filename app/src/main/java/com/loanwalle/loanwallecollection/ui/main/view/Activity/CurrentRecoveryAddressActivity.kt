package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.recoveryaddress.RecoveryRequest
import com.loanwalle.loanwallecollection.data.model.startVisit.StartVisitRequestBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityCurrentRecoveryAddressBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.RecoveryAddressViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.StartVisitViewModel
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.*
import kotlinx.android.synthetic.main.activity_current_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.startvisit
import kotlinx.android.synthetic.main.activity_resest.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CurrentRecoveryAddressActivity : AppCompatActivity() {
     var binding : ActivityCurrentRecoveryAddressBinding? = null

    lateinit var startVisitViewModel : StartVisitViewModel
    lateinit var RecoveryViewModal : RecoveryAddressViewModel
    private val LOCATION_CODE = 1
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var Latitude:Double?=null
    var Longitude:Double?=null
    var CurrentDate:String?=null
    var addName:String?=null
    var lead_id:String?=null
    var loanNub:String?=null
    var FollupAddress:String?=null
    var runningstatus:String?=null
    var running_Leadid:String?=null


    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
        back_layout_currnt.setOnClickListener{
            onBackPressed()
        }

        if (intent.getStringExtra(Constants.RESIDANCE_ADD)!= null){
            addName = intent.getStringExtra(Constants.RESIDANCE_ADD)
            of_res_pre_add.text = addName
            lead_id = intent.getStringExtra(Constants.USER_LEAD_ID)
            loanNub = intent.getStringExtra(Constants.USER_LOAN_NUMBER)
        }
        else if (intent.getStringExtra(Constants.OFFICE_ADD)!= null){
            addName = intent.getStringExtra(Constants.OFFICE_ADD)
            of_res_pre_add.text = addName
        }
        else if (intent.getStringExtra(Constants.PREFERRED_ADD)!= null){
            addName = intent.getStringExtra(Constants.PREFERRED_ADD)
            of_res_pre_add.text = addName
        }


        map.setOnClickListener {
            Log.e("mappp","ook")
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:28.6782,77.3608"))
            i.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity",

            )
            startActivity(i)
        }






         runningstatus=SessionManegar().getString(this,Constants.RUNNING_STATUS)
         running_Leadid=SessionManegar().getString(this,Constants.RUNNING_LEAD_ID)



        if(runningstatus=="1" && running_Leadid!!.equals(lead_id))
        {

            Continue.isVisible=true
            // check for running loan status
            startvisit.isVisible=false
            starttnow.isVisible=false


        }else
        {
            Continue.isVisible=false
            startvisit.isVisible=true
            starttnow.isVisible=true
            //ss start running for collection

        }



        Continue.setOnClickListener {
            val i = Intent(this@CurrentRecoveryAddressActivity, CollectionActivity::class.java)
            startActivity(i)
            finish()
            toast("your running status is Active")
        }



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


        binding!!.startvisit.setOnClickListener {
            showDialog()
        }

        binding!!.starttnow.setOnClickListener{
            // Frist check GPS is on or off , if lat/long is getting then start visit
            if (Latitude== null)
            {
                //
                Toast.makeText(this,"please Enabel gps",Toast.LENGTH_LONG).show()
            }else
            {
                verifyClick()





            }


        }

    }
    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        startVisitViewModel= ViewModelProvider(this, factory).get(StartVisitViewModel::class.java)
        RecoveryViewModal = ViewModelProvider(this, factory).get(RecoveryAddressViewModel::class.java)
         GetCollectionAddress()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        getCurrentDate()
    }

    fun verifyClick() {
        val user_id = SessionManegar().getString(this,Constants.USER_ID)
        val company_id = 2
        val product_id = 2
        val followup_satarted_at = CurrentDate
        val executive_start_letitude = Latitude
        val executive_start_longitude = Longitude
        val loan_no = loanNub
        if (lead_id!= null && company_id!= null && user_id!=null && product_id != null && executive_start_letitude != null &&
            executive_start_longitude != null && followup_satarted_at != null && loan_no !=null) {
            val body = StartVisitRequestBodies.StartVisitRequest(
                company_id,
                executive_start_letitude,
                executive_start_longitude,
                followup_satarted_at,
                lead_id.toString(),
                product_id,
                user_id.toInt() ,
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
                                Log.e("Resopncelogin",message)
                                if (message.equals("success")&& verifyResponse.status.equals("200"))
                                {
                                    //newprogress.errorSnack(message, Snackbar.LENGTH_LONG)
                                        //
                                            //
                                    val follupid:String=verifyResponse.data.followup_id.toString()
                                    SessionManegar().saveString(this,Constants.USER_Follup_id,follupid)
                                    SessionManegar().saveString(this,Constants.RUNNING_LEAD_ID,lead_id.toString())
                                    val i = Intent(this@CurrentRecoveryAddressActivity, CollectionActivity::class.java)
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

    private fun getLastLocation(){
        if (checkLocationPermission()){

            if (isLocationEnabled()){

                fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task ->
                    val loaction : Location? = task.result
                    if (loaction==null){
                        getNullLocation()
                    }else{
//                        location_id.text =" "+ loaction.latitude+" "+ loaction.longitude+
//                                "\n your area "+getCityName(loaction.latitude,loaction.longitude)+
//                                "\n your city "+ getCountryName(loaction.latitude,loaction.longitude)
                        Latitude=loaction.latitude
                        Longitude=loaction.longitude
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
        val locationManger: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
            val lastLocation : Location = p0.lastLocation
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }

    }




    private fun getCityName(lat:Double,lon:Double):String{
        var cityName = ""
        val geocoder = Geocoder(this,Locale.getDefault())
        val address : List<Address> = geocoder.getFromLocation(lat,lon,1)

        cityName = address.get(0).locality
        return cityName
    }

    private fun getCountryName(lat:Double,lon:Double):String{
        var countryName = ""
        val geocoder = Geocoder(this,Locale.getDefault())
        val address : List<Address> = geocoder.getFromLocation(lat,lon,1)

        countryName = address.get(0).getAddressLine(0)
        return countryName
    }


    private fun getCurrentDate(){
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        dateFormatter.setLenient(false)
        val today = Date()
        CurrentDate = dateFormatter.format(today)
    }

    fun GetCollectionAddress() {
        val lead_id = lead_id
        if (lead_id!=null) {
            val body = RecoveryRequest(
                lead_id
            )
            RecoveryViewModal.recoveryAddress(body)
          //  Log.e("BODY",body.toString())
            RecoveryViewModal.recoveryResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                LOG.GetLogError("message",otpResponse.toString())
                              //  Log.e("response",otpResponse.toString())
                                if (message.equals("success")&&otpResponse.status.equals("200"))
                                {
                                    Re_Address.setText(otpResponse.data.residence_address_line2)
                                    Re_City.setText(otpResponse.data.city)
                                    Re_State.setText(otpResponse.data.state)
                                    FollupAddress=Re_Address.text.toString()+" "+Re_City.text.toString()
                                    SessionManegar().saveString(this,Constants.USER_Follup_Address,FollupAddress.toString())
                                }
                                else
                                {
                                 //   newprogress.errorSnack(message, Snackbar.LENGTH_LONG)

                                }
                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                              //  newprogress.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6",message);
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

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.recovery_diloge)
        val Visit_yes = dialog.findViewById(R.id.Visit_yes) as Button
        val Visit_no = dialog.findViewById(R.id.Visit_no) as Button
        Visit_yes.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
            starttnow.setBackgroundResource(R.color.applColor)
            SessionManegar().saveString(this,Constants.RUNNING_LEAD_ID,lead_id.toString())
            SessionManegar().saveString(this,Constants.RUNNING_STATUS,"1")
            dialog.dismiss()

        }


        Visit_no.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }



    private fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
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

}