package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityLoanBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoanDetailsViewModal
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_loan.*

class LoanActivity : AppCompatActivity() {
    lateinit var loanDetailsViewModal : LoanDetailsViewModal
    var binding: ActivityLoanBinding? = null
    var sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loanDetailsViewModal = ViewModelProvider(this, factory).get(LoanDetailsViewModal::class.java)
        getloandetails()
    }
    fun getloandetails() {
        var user = sessionManegar.getString(this,"userid")

        val userid = user
        if (userid!=null) {
            val body = LoanDetailsReq(
                "2","2457","2",userid)
            loanDetailsViewModal.userProfile(body)
            Log.e("BODY",body.toString())
            loanDetailsViewModal.userProfileResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin7",message)
                                if (message.equals("success")&&otpResponse.status.equals("200"))
                                {

                                    Log.e("Resopncelogin5",otpResponse.data.loan_no)
                                    applicationno.text=otpResponse.data.application_no
                                    loanno.text=otpResponse.data.loan_no
                                    loan_sanctioned.text=otpResponse.data.loan_sanctioned
                                    processing_fee.text=otpResponse.data.processing_fee
                                    tenur_roi.text = otpResponse.data.roi
                                    tenure_days.text = otpResponse.data.tenure
                                    loan_disbursal.text = otpResponse.data.net_disbursal_amount
                                    disbursal_date.text = otpResponse.data.disbursal_date
                                    repayment_date.text = otpResponse.data.repayment_date
                                    repayements_amount.text = otpResponse.data.payable_amount.toString()
                                    payment_received.text = otpResponse.data.payment_recived
                                    balance_due.text = otpResponse.data.balance_due.toString()
                                    day_past_due.text = otpResponse.data.day_past_due.toString()
                                    panel_roi.text = otpResponse.data.penel_roi
                                    panel_intrst_perday.text = otpResponse.data.penal_interest_per_day.toString()
                                    inteset_payble.text = otpResponse.data.interest_payable.toString()
                                    total_payble.text = otpResponse.data.total_payable.toString()
                                    name_admine.text = otpResponse.data.name
                                    mobile_admin.text = otpResponse.data.mobile_no




                                    loan_progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                                else

                                {
                                    loan_progress.errorSnack(message, Snackbar.LENGTH_LONG)

                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                loan_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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
        loan_progress.visibility = View.GONE
    }
    fun showProgressBar() {
        loan_progress.visibility = View.VISIBLE
    }
}