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
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_otp.*

class ForgotPasswordActivity : AppCompatActivity() {

    var binding: ActivityOtpBinding? = null

    private var view: View? = null
    lateinit var otpViewModel: OtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()

    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        otpViewModel = ViewModelProvider(this, factory).get(OtpViewModel::class.java)
    }


    fun onForgotClick(view: View) {
        val mobile = phone_num_forgot.text.toString().trim()
        val userid = 113
        if (mobile.isNotEmpty() && userid!=null) {
            val body = RequestOtpBody.RequestOtp(
                mobile,
                userid
            )



            otpViewModel.loginOtp(body)



            otpViewModel.otpResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()

                            var intent = Intent(this,VerifyOTPActivity::class.java)
                            intent.putExtra("number",mobile)
                            startActivity(intent)

                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success!")&&otpResponse.user_id!=null)
                                {
                                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                                    progress1.errorSnack(message, Snackbar.LENGTH_LONG)
                                    /* Intent(this@LoginActivi, OtpActivity::class.java).also {
                                         startActivity(it)*/

                                    Toast.makeText(this,"hiiiii",Toast.LENGTH_SHORT).show()


                                }
                                else

                                {
                                    progress1.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress1.errorSnack(message, Snackbar.LENGTH_LONG)
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
        progress1.visibility = View.GONE
    }
    fun showProgressBar() {
        progress1.visibility = View.VISIBLE
    }
}