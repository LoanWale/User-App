package com.loanwalle.loanwallecollection.Activity

import android.Manifest.permission
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.loanwalle.loanwallecollection.databinding.ActivityLoginBinding
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.view.View
import android.content.DialogInterface

import android.os.Build

import com.google.android.material.snackbar.Snackbar

import android.content.pm.PackageManager

import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import android.R
import android.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    private var view: View? = null
    var binding: ActivityLoginBinding? = null


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

        binding!!.loginBtn.setOnClickListener({ v ->
            //Toast.makeText(applicationContext,"Hello",Toast.LENGTH_LONG).show();
            val intent = Intent(this,OtpActivity::class.java)
            startActivity(intent)
        })

        forgot_passwrd.setOnClickListener{
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }


    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@LoginActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
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


                    val i = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(i)


//                    Snackbar.make(
//                    view!!,
//                    "Permission Granted, Now you can access location data and camera.",
//                    Snackbar.LENGTH_LONG
//                ).show()
//
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
}