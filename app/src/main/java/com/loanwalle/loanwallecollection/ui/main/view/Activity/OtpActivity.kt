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
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_verify_otpactivity.*

class OtpActivity : AppCompatActivity() {
    var binding: ActivityOtpBinding? = null

    lateinit var otpViewModel: OtpViewModel

    private var view: View? = null
    lateinit var verifyViewModel: VerifyOtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        init1()

        init()
        requestOTP()


        binding!!.otpSubmit.setOnClickListener({


            submitClick()

           /* //Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)*/
        })


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
        val mobile = "9034799606"
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
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success!")&&otpResponse.user_id!=null)
                                {
                                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                   /* Intent(this@LoginActivi, OtpActivity::class.java).also {
                                        startActivity(it)*/
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
        val mobile = otp_text.text.toString().toInt()
        val userid = 113
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
                                if (message.equals("success!")&&verifyResponse.message.equals("success!"))
                                {
                                    Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                    /* Intent(this@LoginActivi, OtpActivity::class.java).also {
                                         startActivity(it)*/

                                    Toast.makeText(this,"hiiiii", Toast.LENGTH_SHORT).show()


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