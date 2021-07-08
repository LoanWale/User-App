package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.todaylead.TodayleadRequ
import com.loanwalle.loanwallecollection.data.model.token.TokenRequest
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityHomePageBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.TodayLeadAdp
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TodayLeadViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TokenViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_home_page.Verification_layout
import kotlinx.android.synthetic.main.activity_home_page.back_layout
import kotlinx.android.synthetic.main.activity_home_page.btn_collection
import kotlinx.android.synthetic.main.activity_home_page.btn_verification
import kotlinx.android.synthetic.main.activity_home_page.verify
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_otp.progress
import kotlinx.android.synthetic.main.activity_total_leads.*
import java.util.*

class HomePageActivity : AppCompatActivity() {
    var binding: ActivityHomePageBinding? = null
    lateinit var userProfileViewModel : UserProfileViewModel
    private lateinit var viewModel: TodayLeadViewModel
    private lateinit var tokenViewModel: TokenViewModel
    val sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()

        back_layout.setOnClickListener{
            onBackPressed()
        }

      sessionManegar.getBoolean(this,"checked")

//        collection_layout!!.visibility= View.VISIBLE
//        Verification_layout!!.visibility= View.GONE

//        binding!!.collid.setOnClickListener{
//            val intent1 = Intent(this,LoanDetailActivity::class.java)
//            startActivity(intent1)
//        }


        btn_collection!!.setOnClickListener{
            Verification_layout!!.visibility=View.GONE
            rec_todaycollection!!.visibility=View.VISIBLE
            btn_collection!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_verification!!.setBackgroundResource(R.drawable.graybackground)
        }

        btn_verification!!.setOnClickListener{
            Verification_layout!!.visibility=View.VISIBLE
            rec_todaycollection!!.visibility=View.GONE
            btn_verification!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_collection!!.setBackgroundResource(R.drawable.graybackground)
        }

        back_layout!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Navigation::class.java)
            startActivity(i)
          //  finish()

        }


//        collid!!.setOnClickListener{
//            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
//            val i = Intent(this@HomePageActivity, LoanDetailActivity::class.java)
//            startActivity(i)
//
//        }


        verify!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Verification::class.java)
            startActivity(i)


        }

    }


    private fun init() {
        rec_todaycollection.setHasFixedSize(true)
        rec_todaycollection.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(TodayLeadViewModel::class.java)
        userProfileViewModel = ViewModelProvider(this, factory).get(UserProfileViewModel::class.java)
        tokenViewModel = ViewModelProvider(this, factory).get(TokenViewModel::class.java)

        getTodayLead()
        UpdateToken ()
    }

    fun getTodayLead() {
        val userid = sessionManegar.getString(this,Constants.USER_ID)
        if (userid!=null) {
            val body = TodayleadRequ.LeadRequest(userid)
            viewModel.todaylead(body)
            viewModel.leadResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse!!.message
                                Log.e("Resopncelogin",otpResponse.toString());
                                val status = otpResponse!!.data
                                if (status.isEmpty()){
                                   binding!!.noDataFound.isVisible = true
                                    binding!!.VerificationLayout.isVisible = false
                                }else{
                                    binding!!.noDataFound.isVisible = false
                                    binding!!.VerificationLayout.isVisible = true
                                    binding!!.collectioncount.setText(otpResponse.data.size.toString())
                                    binding!!.verificationCount.setText(otpResponse.data.size.toString())
                                }
                                val picsAdapter = status?.let {
                                    TodayLeadAdp(this@HomePageActivity, it)

                                }
                                //progress4.errorSnack(message, Snackbar.LENGTH_LONG)
                                rec_todaycollection.adapter = picsAdapter



                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress4.errorSnack(message, Snackbar.LENGTH_LONG)
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
        progress4.visibility = View.GONE
    }
    fun showProgressBar() {
        progress4.visibility = View.VISIBLE
    }

    // update fibase token
   public fun UpdateToken ()
    {
        val Token = SessionManegar().getString(this,Constants.USER_TOKEN)
        val Userid =   "44" //SessionManegar().getString(this,Constants.USER_ID)
        if (Token!!.isNotEmpty() && Userid!!.isNotEmpty()) {
            val body = TokenRequest(
                Token,Userid
                )
            tokenViewModel.loginUser(body)
            tokenViewModel.loginResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {

                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { loginResponse ->
                                val message:String= loginResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success"))
                                {
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


