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
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_verify_otpactivity.*

class VerifyOTPActivity : AppCompatActivity() {

    private var view: View? = null
    lateinit var verifyViewModel: VerifyOtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otpactivity)

        var intent = intent
        var num = intent.getStringExtra("number")

        number_phon.text = num

        //init()

        complete_btn_otp.setOnClickListener{
            var intent = Intent(this,ResestActivity::class.java)
            startActivity(intent)

        }
    }


    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        verifyViewModel = ViewModelProvider(this, factory).get(VerifyOtpViewModel::class.java)
    }


    fun verifyClick() {
        val mobile = 4433
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

                            var intent = Intent(this,VerifyOTPActivity::class.java)
                            intent.putExtra("number",mobile)
                            startActivity(intent)

                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success!")&&verifyResponse.message.equals("success!"))
                                {
                                    Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                                    progress2.errorSnack(message, Snackbar.LENGTH_LONG)
                                    /* Intent(this@LoginActivi, OtpActivity::class.java).also {
                                         startActivity(it)*/

                                    Toast.makeText(this,"hiiiii", Toast.LENGTH_SHORT).show()


                                }
                                else

                                {
                                    progress2.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress2.errorSnack(message, Snackbar.LENGTH_LONG)
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
        progress2.visibility = View.GONE
    }
    fun showProgressBar() {
        progress2.visibility = View.VISIBLE
    }

}