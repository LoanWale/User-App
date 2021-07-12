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
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.NewPasswordViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyPasswordViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_resest.*
import kotlinx.android.synthetic.main.activity_verify_otpactivity.*

class ResestActivity : AppCompatActivity() {
    lateinit var varifyPasswordViewModel : NewPasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resest)

        init()
        image_back.setOnClickListener{
            onBackPressed()
        }

        send_pswrd_btn.setOnClickListener{
            verifyClick()
        }
    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        varifyPasswordViewModel = ViewModelProvider(this, factory).get(NewPasswordViewModel::class.java)
    }
    fun verifyClick() {
        val new_password = editText_new_pass.text.toString()
        val confirm_password = editText_confirm_passwrd.text.toString()
        val userid = SessionManegar().getString(this,SessionManegar().getString(this,com.loanwalle.loanwallecollection.util.Constants.USER_ID))
        if (new_password!= null && confirm_password!= null && userid!=null) {
            val body = NewPasswordRequestBodies.NewPasswordRequest(
                new_password,
                confirm_password,
                userid
            )



            varifyPasswordViewModel.verfiyPassword(body)



            varifyPasswordViewModel.newPassword.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()


                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("password changed Successfully")&&verifyResponse.status.equals("200"))
                                {

                                    //new_progress.errorSnack(message, Snackbar.LENGTH_LONG)

                                    var intent = Intent(this,LoginActivity::class.java)
                                    startActivity(intent)



                                }
                                else

                                {
                                    new_progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                new_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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
        new_progress.visibility = View.GONE
    }
    fun showProgressBar() {
        new_progress.visibility = View.VISIBLE
    }
}