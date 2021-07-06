package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.loanwalle.loanwallecollection.databinding.ActivityUnlockBinding
import okhttp3.internal.notify

class UnlockActivity : AppCompatActivity() {

    var binding : ActivityUnlockBinding? = null

    private var cancellationSignal : CancellationSignal?=null
    private val authenticationCallback : BiometricPrompt.AuthenticationCallback

    get() = object :BiometricPrompt.AuthenticationCallback(){
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            notifiyUser("AuthenticationError :$errString")
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            super.onAuthenticationSucceeded(result)
            notifiyUser("Authentication Success")
           /* var intent = Intent(this@UnlockActivity,HomePageActivity::class.java)
            startActivity(intent)*/
        }
    }

    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnlockBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        checkBiometricSupport()


        binding!!.usePatt.setOnClickListener{
            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Title of propot")
                .setSubtitle("Auth required")
                .setDescription("This App is use FingerPrint for your Data is secure")
                .setNegativeButton("Cancel",this.mainExecutor,DialogInterface.OnClickListener{
                    dialog, which ->
                    notifiyUser("Auth Cancelled")
                }).build()
            biometricPrompt.authenticate(getCancelSingnal(),mainExecutor,authenticationCallback)
        }


    }

    private fun getCancelSingnal(): CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifiyUser("Auth is cancel by user")
        }
        return cancellationSignal as CancellationSignal
    }
    private fun checkBiometricSupport():Boolean {
        val keyguardManager : KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure){
            notifiyUser("Finger print not enable")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)!=
                PackageManager.PERMISSION_GRANTED){
            notifiyUser("permission in not enable")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        }else true

    }

    /*private fun getManagers(): Boolean {
        keyguardManager = getSystemService(Context.KEYGUARD_SERVICE)
                as KeyguardManager
        fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE)
                as FingerprintManager

        if (keyguardManager?.isKeyguardSecure == false) {

            Toast.makeText(this,
                "Lock screen security not enabled in Settings",
                Toast.LENGTH_LONG).show()
            return false
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.USE_FINGERPRINT) !=
            PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,
                "Fingerprint authentication permission not enabled",
                Toast.LENGTH_LONG).show()

            return false
        }

        if (fingerprintManager?.hasEnrolledFingerprints() == false) {
            Toast.makeText(this,
                "Register at least one fingerprint in Settings",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }*/

    private fun notifiyUser(message:String){

    }
}