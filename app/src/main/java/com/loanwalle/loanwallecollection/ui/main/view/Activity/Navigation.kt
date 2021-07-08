package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.getTodayCountLead.GetCountLeadRequest
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityNavigationBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetCountLeadViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetUserProfileViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_navigation.*


class Navigation : AppCompatActivity() {

    lateinit var getCountLeadViewModel: GetCountLeadViewModel
    lateinit var userProfileViewModel: GetUserProfileViewModel
    var binding: ActivityNavigationBinding?=null
    val sessionManegar = SessionManegar()

    private var cancellationSignal : CancellationSignal?=null
    private val authenticationCallback : BiometricPrompt.AuthenticationCallback

        get() = @RequiresApi(Build.VERSION_CODES.P)
        object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifiyUser("AuthenticationError :$errString")
            }
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifiyUser("Authentication Success")
               /* var intent = Intent(this@Navigation,HomePageActivity::class.java)
                startActivity(intent)*/
                sessionManegar.saveBoolean(this@Navigation,"checked",true)
                tooggllee.isChecked = true

            }
        }
    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        menu.setOnClickListener{
            onBackPressed()
        }

        binding!!.tooggllee.setOnCheckedChangeListener({
            _, isChecked ->
            val message = if(isChecked){
            sessionManegar.saveBoolean(this,"checked",true)
            }else{}
        })

        if (tooggllee.isChecked){
            sessionManegar.saveBoolean(this@Navigation,"checked",true)


        }





        checkBiometricSupport()


        binding!!.tooggllee.setOnClickListener{
            if (!tooggllee.isChecked){
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Are You Sure Disable to FingerPrint")
                    .setNegativeButton("Disable") { dialog, which ->
                        tooggllee.isChecked = false
                    }.setPositiveButton("No"){
                            dialog, which ->
                        tooggllee.isChecked = true
                    }.show()
                dialog.show()

            }else{

                val biometricPrompt = BiometricPrompt.Builder(this)
                    .setTitle("Unlock your LW Collex")
                    .setSubtitle("Confirm Your Screen Lock")
                    .setDescription("This App is use FingerPrint for your Data is secure")
                    .setNegativeButton("Cancel",this.mainExecutor, DialogInterface.OnClickListener{
                            dialog, which ->
                        notifiyUser("Auth Cancelled")


                    }).build()
                biometricPrompt.authenticate(getCancelSingnal(),mainExecutor,authenticationCallback)
            }


        }



        init()
        updateLead()


        convey_reprt.setOnClickListener{
            var i = Intent(this,Covence_Report::class.java)
            startActivity(i)
        }

        binding!!.profile.setOnClickListener{
            var intent1 = Intent(this,Collection_Profile::class.java)
            startActivity(intent1)
        }


           binding!!.todayld.setOnClickListener{
            var intent1 = Intent(this,HomePageActivity::class.java)
            startActivity(intent1)
        }
           binding!!.totaled.setOnClickListener{
            var intent1 = Intent(this,Total_Leads::class.java)
            startActivity(intent1)
        }
           binding!!.ircontact.setOnClickListener{
            var intent1 = Intent(this,IRAActivity::class.java)
            startActivity(intent1)
        }



        logout.setOnClickListener{
            sessionManegar.clearAll(this)
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun init(){
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application,repository)
        getCountLeadViewModel = ViewModelProvider(this,factory).get(GetCountLeadViewModel::class.java)
        userProfileViewModel = ViewModelProvider(this,factory).get(GetUserProfileViewModel::class.java)
        userProfile()
    }

    private fun userProfile(){
        val userid = 44
        if (userid!=null) {
            val body = GetUserProfileBodies.GetUserProfileRequest(
                userid
            )

            userProfileViewModel.getUserProfile(body)

            userProfileViewModel.getUserProfile.observe(this,{ event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()


                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin11",message);
                                if (message.equals("success")&&verifyResponse.status.equals("200"))
                                {
                                   // Toast.makeText(this,)
                                       val empid = verifyResponse.data.get(0).name.substring(0,4) +" "+ verifyResponse.data.get(0).mobile.substring(6,10)
                                       name.text = verifyResponse.data.get(0).name

                                    phone.text ="Emp Id "+ empid
                                    Glide.with(this).load(verifyResponse.data.get(0).profile_pic).into(btncamera)
                                //nav_progreess.errorSnack(message, Snackbar.LENGTH_LONG)

                                }
                                else

                                {
                                    nav_progreess.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                nav_progreess.errorSnack(message, Snackbar.LENGTH_LONG)
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

    private fun updateLead(){
            val userid = "44"
            if (userid!=null) {
                val body = GetCountLeadRequest(
                    userid
                )

               getCountLeadViewModel.getCountslead(body)
                Log.e("BODY",body.toString())



                getCountLeadViewModel.getCountlead.observe(this, Observer { event ->
                    event.getContentIfNotHandled()?.let { response ->
                        when (response) {
                            is Resource.Success -> {
                                hideProgressBar()

                                response.data?.let { otpResponse ->
                                    val message:String= otpResponse.message
                                    Log.e("Resopncelogin7",otpResponse.toString())
                                    if (message.equals("success")&&otpResponse.status.equals("200"))
                                    {
                                        val todayLead = otpResponse.No_of_todays_leads.toString()
                                        binding!!.todayLeadCount.text = todayLead

                                        total_lead_count.text = otpResponse.old_leads_count.toString()

                                    }
                                    else

                                    {
                                        //nav_progress.errorSnack(message, Snackbar.LENGTH_LONG)

                                    }


                                }
                            }

                            is Resource.Error -> {
                                hideProgressBar()
                                response.message?.let { message ->
                                    //nav_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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
        //nav_progress.visibility = View.GONE
    }
    fun showProgressBar() {
        //nav_progress.visibility = View.VISIBLE
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
    private fun notifiyUser(message:String){

        if (message.equals("Auth Cancelled")){
            tooggllee.isChecked = false
        }else tooggllee.isChecked = true


    }

    private fun toggleChecked(){
        if (tooggllee.isChecked){
            val dialog = AlertDialog.Builder(this)
                .setTitle("Are You Sure Disable to FingerPrint")
                .setNegativeButton("Disable") { dialog, which ->
                    tooggllee.isChecked = false
                }.setPositiveButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }.show()
        }

    }
}