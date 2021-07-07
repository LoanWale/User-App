package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotRequestBodies
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.ForgotPasswordViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_otp.*

class ForgotPasswordActivity : AppCompatActivity() {

    var binding: ActivityOtpBinding? = null
    lateinit var otpViewModel: ForgotPasswordViewModel
    var sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        otp_send.setOnClickListener{
            onForgotClick()
        }

        imageView8.setOnClickListener{
            onBackPressed()
        }

    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        otpViewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
    }


    fun onForgotClick() {
        val mobile = phone_num_forgot.text.toString().trim()
        if (mobile.isNotEmpty()) {
            val body = ForgotRequestBodies.ForgotRequest(
                mobile,
            )

            otpViewModel.forgotPassword(body)
            otpViewModel.userForgotPassword.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()

                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin",message);


                                if (message.equals("OTP sent Successfully")&&otpResponse.user_id!=null)
                                {
                                    sessionManegar.saveString(this, "mobile",mobile,)
                                    sessionManegar.saveInt(this,"otpCode",otpResponse.otp_code)
                                    sessionManegar.saveString(this, "userId",otpResponse.user_id)
                                    //progress9.errorSnack(message, Snackbar.LENGTH_LONG)
                                    var intent = Intent(this,VerifyOTPActivity::class.java)
                                    startActivity(intent)
                                }
                                else {
                                    progress9.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress9.errorSnack(message, Snackbar.LENGTH_LONG)
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
        progress9.visibility = View.GONE
    }
    fun showProgressBar() {
        progress9.visibility = View.VISIBLE
    }
}