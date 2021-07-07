package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.previousPayment.PreviousPaymentRequest
import com.loanwalle.loanwallecollection.data.model.token.TokenRequest
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.PreviousPayAdp
import com.loanwalle.loanwallecollection.ui.main.adapter.TotalLeadAdp
import com.loanwalle.loanwallecollection.ui.main.viewmodel.PreviousPaymentViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TodayLeadViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TokenViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import com.loanwalle.loanwallecollection.utils.toast
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_previous_payment.*
import kotlinx.android.synthetic.main.activity_total_leads.*


class PreviousPayment : AppCompatActivity() {

    lateinit var previousPaymentViewModel: PreviousPaymentViewModel
    private lateinit var recycler : RecyclerView
    //private lateinit var userAdapter : UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_payment)

        back_layout_previous.setOnClickListener{
            onBackPressed()
        }

        init()



       // recycler.adapter = userAdapter
    }
    private fun init() {
        re_pymnt_recycler_view.setHasFixedSize(true)
        re_pymnt_recycler_view.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }
    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        previousPaymentViewModel = ViewModelProvider(this, factory).get(PreviousPaymentViewModel::class.java)

        onLoginClick()
    }
    fun onLoginClick() {
        val user_id = "44"
        val company_id = "2"
        val product_id = "2"
        val from_date = "2021-06-30"
        val to_date = "2021-07-07"

        if (user_id!!.isNotEmpty() && company_id!!.isNotEmpty() && product_id!!.isNotEmpty()
            && from_date!!.isNotBlank()&& to_date!!.isNotEmpty()) {
            val body = PreviousPaymentRequest(
                user_id,
                company_id,
                product_id,
                from_date,
                to_date
            )
            previousPaymentViewModel.paymetUser(body)
            previousPaymentViewModel.paymentResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {

                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { loginResponse ->
                                val message:String= loginResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success"))
                                {
                                    toast("Response Success")
                                    val status = loginResponse.data

                                    Log.e("paymenthistory",status.toString());

                                    //   Toast.makeText(this,loginResponse.data.get(0).paid_amount, Toast.LENGTH_LONG).show()
                                    val picsAdapter = status?.let {
                                        PreviousPayAdp(
                                            this@PreviousPayment,
                                            it
                                        )
                                    }
                                    re_pymnt_recycler_view.adapter = picsAdapter
                                }else

                                {
                                    payment_progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                payment_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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
        payment_progress.visibility = View.GONE
    }
    fun showProgressBar() {
            payment_progress.visibility = View.VISIBLE
    }
}