package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel
import com.loanwalle.loanwallecollection.utils.ConstantsSave
import com.loanwalle.loanwallecollection.utils.OTP.SMSReceiver
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_otp.progress

class OtpActivity : AppCompatActivity() ,
   SMSReceiver.OTPReceiveListener {
    var binding: ActivityOtpBinding? = null

    lateinit var otpViewModel: OtpViewModel

    private var view: View? = null
    lateinit var verifyViewModel: VerifyOtpViewModel


    var sessionManegar = SessionManegar()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var num =sessionManegar.getString(this,"mobile")

        textView5.text = num








        init()
        requestOTP()


       otp_submitClick.setOnClickListener{

           init1()
           submitClick()
       }




    }

    private fun init1() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        verifyViewModel = ViewModelProvider(this, factory).get(VerifyOtpViewModel::class.java)
    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        otpViewModel = ViewModelProvider(this, factory).get(OtpViewModel::class.java)
    }
    override fun onOTPReceived(otp: String) {
        //  showToast("OTP Received: " + otp);
        otp_text.setText("")
        otp_text.setText(otp.substring(15, 21))

//        if (a.equals("1")) {
//
//           getLoginVerify(pinView.getText().toString());
//
//        } else {
//
//            getPhoneverification(pinView.getText().toString());
//
//        }

    }

    override fun onOTPTimeOut() {
        // showToast("OTP Time out");
    }

    override fun onOTPReceivedError(error: String?) {
        // showToast(error);
    }

    fun requestOTP() {
        var user =sessionManegar.getString(this@OtpActivity,sessionManegar.USER_ID)
        var mob =sessionManegar.getString(this@OtpActivity,"mobile")
        val mobile = mob.toString()
        val user_id = user
        if (mobile.isNotEmpty() && user_id!=null) {
            val body = RequestOtpBody.RequestOtp(
                mobile,
                user_id.toString()
            )



            otpViewModel.loginOtp(body)



            otpViewModel.otpResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()


                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("OTP sent Successfully")&&otpResponse.user_id != null)
                                {
                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)

                                }
                                else

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

    fun hideProgressBar() {
        progress.visibility = View.GONE
    }
    fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

    fun submitClick() {
        var use =sessionManegar.getString(this@OtpActivity,sessionManegar.USER_ID)
        val mobile = otp_text.text.toString().toInt()
        val user_id = use.toString().toInt()
        if (mobile!= null && user_id!=null) {
            val body = VerifyRequestBody.VerifyRequest(
                mobile,
                user_id
            )



            verifyViewModel.verifyOtp(body)



            verifyViewModel.verifyResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()



                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("Login success!")&&verifyResponse.status.equals("200"))
                                {

                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)



                                    sessionManegar.saveInt(this@OtpActivity,sessionManegar.LOGIN_STATE,
                                        ConstantsSave.LoginFlow.HOMESCREEN)
                                    var intent = Intent(this,HomePageActivity::class.java)
                                    startActivity(intent)
                                    finish()




                                }
                                else

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

}