package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.collection.Collection_Request
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityCollectionBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoanDetailsViewModal
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoanUpdateViewModal
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import com.loanwalle.loanwallecollection.utils.toast
import kotlinx.android.synthetic.main.activity_collection.*
import java.text.SimpleDateFormat
import java.util.*


class CollectionActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    lateinit var loanDetailsViewModal: LoanDetailsViewModal
    lateinit var loanupdateViewMod: LoanUpdateViewModal
    var binding: ActivityCollectionBinding? = null
    var closertype = ""
    var paymentstatus = "1"
    var Total_Payable_Amount = ""
    var net_disbursal_amount = ""
    var balanceintrest: Int? = null

    // Get location

    private val LOCATION_CODE = 1

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var Latitude: Double? = null
    var Longitude: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        // inialize

        initializationn()


        // next_sch_date.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                next_sch_date.text = "Next Schedule Date " + sdf.format(cal.time)

            }

        img_calender.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }



if (SessionManegar().getString(this,Constants.COLLECTION_RUNNING).equals("1"))
{
    val intent = Intent(this, PaymentActivity::class.java)
    startActivity(intent)
    finish()
}

        if (!checkGPSEnabled()) {
            return
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                //Location Permission already granted
                Debug.getLocation();
            } else {
                //Request Location Permission
                checkLocationPermission()
            }
        } else {
            Debug.getLocation();
        }




        submit.setOnClickListener {


            if (principlea.text.toString().isEmpty()) {
                toast("please enter amount ")

            } else if (closertype.isEmpty()) {
                toast("please select Closer type")

            } else if (Collection_Remark.text.toString().isEmpty()) {
                toast("please enter remark")

            } else if (Edit_KM.text.toString().isEmpty()) {
                toast("please enter travel distance")

            } else if (paymentstatus.equals("2") || paymentstatus.equals("3")) {

                if (next_sch_date.text.toString().equals("Next Schedule Date")) {
                    toast("please select Schedule Date")
                } else {

                    update_Loan_Detils()

                }


            }else
            {

                update_Loan_Detils()

            }


            // direct paymet page
        }

         radio_reg.setOnClickListener {

            if (radio_reg.isChecked) {
                closertype = "1"
                radio_writeoff.setChecked(false)
                radi_discount.setChecked(false)
                radio_settalment.setChecked(false)
                part_radio.setChecked(false)

            } else {
                radio_reg.setChecked(false)
                closertype = ""

            }

        }


        radio_settalment.setOnClickListener {
            if (radio_settalment.isChecked) {
                closertype = "2"
                radio_writeoff.setChecked(false)
                radi_discount.setChecked(false)
                radio_reg.setChecked(false)
                part_radio.setChecked(false)

            } else {
                radio_settalment.setChecked(false)
                closertype = ""

            }
        }

        radio_writeoff.setOnClickListener {
            if (radio_writeoff.isChecked) {
                closertype = "3"

                radio_settalment.setChecked(false)
                radi_discount.setChecked(false)
                radio_reg.setChecked(false)
                part_radio.setChecked(false)

            } else {
                radio_writeoff.setChecked(false)
                closertype = ""

            }
        }
        radi_discount.setOnClickListener {
            if (radi_discount.isChecked) {
                closertype = "4"
                radi_discount.setChecked(true)
                radio_settalment.setChecked(false)
                radio_writeoff.setChecked(false)
                radio_reg.setChecked(false)
                part_radio.setChecked(false)

            } else {

                radi_discount.setChecked(false)
                closertype = ""

            }

        }


         part_radio.setOnClickListener {
                if (part_radio.isChecked) {
                    closertype = "5"
                    radio_settalment.setChecked(false)
                    radio_writeoff.setChecked(false)
                    radio_reg.setChecked(false)
                    radi_discount.setChecked(false)

//Log.e("partriun","1")
                } else {
                    part_radio.setChecked(false)
                    closertype = ""
                  //  Log.e("partriun","0")


                }
            }

        principlea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length > 0) {

                    if (Total_Payable_Amount < principlea.text.toString()) {
                        toast("Amount should be less then repayment amount")
                        paymentstatus = "1"
                        val unpaidamount2: Int =
                            Total_Payable_Amount.toInt() - principlea.text.toString().toInt()
                        unpaidamount.setText(unpaidamount2.toString())
                    } else if (Total_Payable_Amount == principlea.text.toString()) {
                        part_payment.background =
                            resources.getDrawable(R.drawable.part_pyment_drawable)
                        nil_payment.background =
                            resources.getDrawable(R.drawable.part_pyment_drawable)
                        full_payment.background =
                            resources.getDrawable(R.drawable.full_payment_drawable)

                        full_payment.setTextColor(getColor(R.color.white))
                        nil_payment.setTextColor(getColor(R.color.black))
                        part_payment.setTextColor(getColor(R.color.black))

                        next_sch_date.isVisible = false
                        img_calender.isVisible = false
                        paymentstatus = "1"
                        val unpaidamount2: Int =
                            Total_Payable_Amount.toInt() - principlea.text.toString().toInt()
                        unpaidamount.setText(unpaidamount2.toString())

                    } else if (Total_Payable_Amount > principlea.text.toString() && principlea.text.toString()
                            .toInt() >= 1
                    ) {
                        part_payment.background =
                            resources.getDrawable(R.drawable.full_payment_drawable)
                        part_payment.setTextColor(getColor(R.color.white))
                        full_payment.background =
                            resources.getDrawable(R.drawable.part_pyment_drawable)
                        nil_payment.background =
                            resources.getDrawable(R.drawable.part_pyment_drawable)
                        full_payment.setTextColor(getColor(R.color.black))
                        nil_payment.setTextColor(getColor(R.color.black))
                        img_calender.isVisible = true
                        next_sch_date.isVisible = true
                        paymentstatus = "2"
                        val unpaidamount2: Int =
                            Total_Payable_Amount.toInt() - principlea.text.toString().toInt()
                        unpaidamount.setText(unpaidamount2.toString())

                        next_sch_date.setText("Next Schedule Date")

                    } else if (principlea.text.toString().toInt() <= 0) {

                        nil_payment.setTextColor(getColor(R.color.white))
                        nil_payment.background =
                            resources.getDrawable(R.drawable.full_payment_drawable)
                        full_payment.setTextColor(getColor(R.color.black))
                        full_payment.background =
                            resources.getDrawable(R.drawable.part_pyment_drawable)
                        part_payment.setTextColor(getColor(R.color.black))
                        part_payment.background = getDrawable(R.drawable.part_pyment_drawable)
                        next_sch_date.isVisible = true
                        img_calender.isVisible = true
                        val unpaidamount2: Int = Total_Payable_Amount.toInt()
                        unpaidamount.setText(unpaidamount2.toString())
                        paymentstatus = "3"
                        next_sch_date.setText("Next Schedule Date")


                    }

                } else {
                    nil_payment.setTextColor(getColor(R.color.white))
                    nil_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)
                    full_payment.setTextColor(getColor(R.color.black))
                    full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
                    part_payment.setTextColor(getColor(R.color.black))
                    part_payment.background = getDrawable(R.drawable.part_pyment_drawable)
                    next_sch_date.isVisible = true
                    img_calender.isVisible = true
                    paymentstatus = "nill"

                    next_sch_date.setText("Next Schedule Date")

                }
            }


        })
    }


    // setup view Modal and component
    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loanDetailsViewModal =
            ViewModelProvider(this, factory).get(LoanDetailsViewModal::class.java)
        loanupdateViewMod = ViewModelProvider(this, factory).get(LoanUpdateViewModal::class.java)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getloandetails()
        getLastLocation()

    }

    // Get Loan Details
    fun getloandetails() {
        val user =  SessionManegar().getString(this, Constants.USER_ID)
        val userid = user
        if (userid != null) {
            val body = LoanDetailsReq(
                "2", SessionManegar().getString(this,Constants.RUNNING_LEAD_ID).toString(), "2", userid
            )
            loanDetailsViewModal.userProfile(body)
            Log.e("BODY", body.toString())
            loanDetailsViewModal.userProfileResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message: String = otpResponse.message
                                Log.e("Resopncelogin7", message)
                                if (message.equals("success") && otpResponse.status.equals("200")) {

                                    Log.e("Resopncelogin5", otpResponse.data.loan_no)
                                    Total_Payable_Amount =
                                        otpResponse.data.payable_amount.toString()
                                    net_disbursal_amount =
                                        otpResponse.data.net_disbursal_amount.toString()
                                    text_balnce.text = Total_Payable_Amount
                                    principlea.setText(Total_Payable_Amount)
                                    balanceprinciple.setText(net_disbursal_amount)
                                    //
                                    balanceintrest = otpResponse.data.payable_amount - otpResponse.data.net_disbursal_amount
                                    balance_totalintrest.setText(balanceintrest.toString())
                                    totalpaybleamount.setText(Total_Payable_Amount)
                                    unpaidamount.setText("0")

                                    //    prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                } else {
                                    prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6", message);
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
        prog_ress.visibility = View.GONE
    }

    fun showProgressBar() {
        prog_ress.visibility = View.VISIBLE
    }


    public fun initializationn() {

        part_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
        nil_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
        full_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)
        full_payment.setTextColor(getColor(R.color.white))
        nil_payment.setTextColor(getColor(R.color.black))
        part_payment.setTextColor(getColor(R.color.black))

        next_sch_date.isVisible = false
        img_calender.isVisible = false
        setupViewModel()
    }


    fun update_Loan_Detils() {

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val time = SimpleDateFormat("hh-mm-ss", Locale.getDefault()).format(Date())


        val user =  SessionManegar().getString(this, Constants.USER_ID)
        val userid = user
        if (userid != null) {
            val body = Collection_Request(
                unpaidamount.text.toString(),
                Latitude.toString(),
                Longitude.toString(),
                date,
                time,
                SessionManegar().getString(this,Constants.USER_Follup_id).toString(),
                Collection_Remark.text.toString(),
                next_sch_date.text.toString().trim().replace("Next Schedule Date", ""),
                principlea.text.toString(),
                paymentstatus,
                "",
                Collection_Remark.text.toString(),
                Edit_KM.text.toString(),
                user,
                SessionManegar().getString(this,Constants.USER_Follup_Address).toString(),
                closertype
            )

            loanupdateViewMod.userProfile(body)
            Log.e("BODY", body.toString())
            loanupdateViewMod.userProfileResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message: String = otpResponse.message
                                Log.e("Resopncelogin7", message)
                                if (otpResponse.status.equals("200")) {
                                    val intent = Intent(this, PaymentActivity::class.java)
                                    startActivity(intent)
                                    SessionManegar().saveString(this,Constants.COLLECTION_RUNNING,"1")
                                    //SessionManegar().remove(this, Constants.RUNNING_LEAD_ID)
                                    finish()
                                } else {
                                    prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6", message);
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

    private fun getLastLocation() {
        if (checkLocationPermission()) {

            if (isLocationEnabled()) {

                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val loaction: Location? = task.result
                    if (loaction == null) {
                        getNullLocation()
                    } else {
//
                        Latitude = loaction.latitude
                        Longitude = loaction.longitude
                    }
                }

            } else {
                Toast.makeText(this, "Location not on", Toast.LENGTH_LONG).show()
            }
        } else {
            RequestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun RequestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            ), LOCATION_CODE
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManger: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManger.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getNullLocation() {
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
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val lastLocation: Location = p0.lastLocation
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }

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