package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityLoginBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoginViewModel
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.ConstantsSave
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    private var view: View? = null
    var binding: ActivityLoginBinding? = null
    lateinit var loginViewModel: LoginViewModel
    var sessionManegar = SessionManegar()

    override fun onResume() {
        super.onResume()
        if (!checkPermission()) {
            requestPermission()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PackageManager.PERMISSION_GRANTED
        )


        forgot_passwrd.setOnClickListener{
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }


    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, ACCESS_FINE_LOCATION)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, CAMERA)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {


        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0) {
                val locationAccepted = grantResults[0] === PackageManager.PERMISSION_GRANTED
                val cameraAccepted = grantResults[1] === PackageManager.PERMISSION_GRANTED
                if (locationAccepted && cameraAccepted) {

                    // if already login then goes to home page
                //   val i = Intent(this@LoginActivity, HomeActivity::class.java)
                 //   startActivity(i)
                }
                else {
                    Snackbar.make(
                        view!!,
                        "Permission Denied, You cannot access location data and camera.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel(
                                "You need to allow access to both the permissions"
                            ) { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(
                                        arrayOf(ACCESS_FINE_LOCATION, CAMERA),
                                        PERMISSION_REQUEST_CODE
                                    )
                                }
                            }
                            return
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    fun onLoginClick(view: View) {
        val mobilenono = mobileno.text.toString()
        val password = password_new.text.toString()
        if (mobilenono.isNotEmpty() && password.isNotEmpty()) {
            val body = RequestBodies.LoginBody(
                mobilenono,
                password)
            loginViewModel.loginUser(body)
            loginViewModel.loginResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { loginResponse ->
                                val message:String= loginResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success!")&&loginResponse.USERID!=null)
                                {
                                    //progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                    sessionManegar.saveInt(this@LoginActivity,sessionManegar.LOGIN_STATE,ConstantsSave.LoginFlow.Otpscreen)
                                    sessionManegar.saveString(this@LoginActivity,Constants.USER_ID,loginResponse.USERID)
                                    sessionManegar.saveString(this@LoginActivity,Constants.USER_MOBILE,mobilenono)
                                    Intent(this@LoginActivity, OtpActivity::class.java).also {
                                        it.putExtra(Constants.USER_MOBILE,mobilenono)
                                        startActivity(it)
                                        finish()
                                    }
                                }else

                                {
                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress.errorSnack(message, Snackbar.LENGTH_LONG)
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

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@LoginActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }


}