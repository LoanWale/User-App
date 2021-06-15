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
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityRecoveryAddressBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_recovery_address.*

class Recovery_Address : AppCompatActivity() {
    var binding: ActivityRecoveryAddressBinding? = null


    lateinit var userProfileViewModel : UserProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        init()
        requestUserProfile()

        binding!!.backLayout.setOnClickListener {
           finish()
        }

        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
            collectnow!!.setBackgroundResource(R.color.applColor)


        }


       binding!!.collectnow.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@Recovery_Address, CollectionActivity::class.java)
            startActivity(i)
           finish()
        }


    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        userProfileViewModel = ViewModelProvider(this, factory).get(UserProfileViewModel::class.java)
    }

    fun requestUserProfile() {
        val userid = "2457"
        if (userid!=null) {
            val body = UserProfileBody.UserProfileRequest(
                userid
            )


            userProfileViewModel.userProfile(body)
            Log.e("BODY",body.toString())


            userProfileViewModel.userProfileResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()

                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin7",otpResponse.toString())
                                if (message.equals("success")&&otpResponse.status.equals("200"))
                                {

                                   phoneno.setText(otpResponse.data.residence_address_line2)
                                    city_add.setText(otpResponse.data.city)
                                    state_add.setText(otpResponse.data.state)

                                    Log.e("Resopncelogin5",otpResponse.data.city)
                                    //Toast.makeText(this,"DEEPAK KUMAR", Toast.LENGTH_SHORT).show()
                                    //progress_add.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                                else

                                {
                                    progress_add.errorSnack(message, Snackbar.LENGTH_LONG)

                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress_add.errorSnack(message, Snackbar.LENGTH_LONG)
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
        progress_add.visibility = View.GONE
    }
    fun showProgressBar() {
        progress_add.visibility = View.VISIBLE
    }
}