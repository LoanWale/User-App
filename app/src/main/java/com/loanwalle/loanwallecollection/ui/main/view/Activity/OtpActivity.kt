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
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_otp.*

class OtpActivity : AppCompatActivity() {
    var binding: ActivityOtpBinding? = null

    lateinit var otpViewModel: OtpViewModel

    private var view: View? = null
    lateinit var verifyViewModel: VerifyOtpViewModel


    var sessionManegar = SessionManegar()



    var preferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        Toast.makeText(this," "+sessionManegar.getString(this@OtpActivity,"userid"),Toast.LENGTH_LONG).show()






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


    fun requestOTP() {
        var user =sessionManegar.getString(this@OtpActivity,"userid")
        val mobile = "9034799606"
        val userid = user.toString().toInt()
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
        var use =sessionManegar.getString(this@OtpActivity,"userid")
        val mobile = otp_text.text.toString().toInt()
        val userid = use.toString().toInt()
        if (mobile!= null && userid!=null) {
            val body = VerifyRequestBody.VerifyRequest(
                mobile,
                userid
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



                                    var intent = Intent(this,HomePageActivity::class.java)
                                    startActivity(intent)




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