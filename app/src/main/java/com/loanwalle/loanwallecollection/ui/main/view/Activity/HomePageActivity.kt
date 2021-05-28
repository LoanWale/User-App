package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityHomePageBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.*

class HomePageActivity : AppCompatActivity() {
    var binding: ActivityHomePageBinding? = null

    lateinit var userProfileViewModel : UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        collection_layout!!.visibility= View.VISIBLE
        Verification_layout!!.visibility= View.GONE




        binding!!.collid.setOnClickListener{
            var intent1 = Intent(this,LoanDetailActivity::class.java)
            startActivity(intent1)
        }


        btn_collection!!.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()

            collection_layout!!.visibility=View.VISIBLE
            Verification_layout!!.visibility=View.GONE
            btn_collection!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_verification!!.setBackgroundResource(R.drawable.graybackground)

        }



        btn_verification!!.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            collection_layout!!.visibility=View.GONE
            Verification_layout!!.visibility=View.VISIBLE
            btn_verification!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_collection!!.setBackgroundResource(R.drawable.graybackground)



        }



        back_layout!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Navigation::class.java)
            startActivity(i)

        }


        collid!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, LoanDetailActivity::class.java)
            startActivity(i)

        }
        verify!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Verification::class.java)
            startActivity(i)

        }

    }


    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        userProfileViewModel = ViewModelProvider(this, factory).get(UserProfileViewModel::class.java)
    }

    fun requestOTP() {
        val userid = 113
        if (userid!=null) {
            val body = UserProfileBody.UserProfileRequest(
                userid
            )



            userProfileViewModel.userProfile(body)




            userProfileViewModel.userProfileResponse.observe(this, Observer { event ->
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
    }


