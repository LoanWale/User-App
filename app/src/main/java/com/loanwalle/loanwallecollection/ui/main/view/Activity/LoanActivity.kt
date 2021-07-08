package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_covence_report.*
import kotlinx.android.synthetic.main.activity_loan.*
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class LoanActivity : AppCompatActivity() {
    lateinit var loanDetailsViewModal : LoanDetailsViewModal
    var binding: ActivityLoanBinding? = null
    var sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupViewModel()

        collect_button.setOnClickListener{
            var intent = Intent(this,CurrentRecoveryAddressActivity::class.java)
            startActivity(intent)
        }
        back_layout_detail.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loanDetailsViewModal = ViewModelProvider(this, factory).get(LoanDetailsViewModal::class.java)
        getloandetails()
    }
    fun getloandetails() {
        val user = "44"// sessionManegar.getString(this, Constants.USER_ID)
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

                                    val separator: Char =
                                        DecimalFormatSymbols.getInstance().getDecimalSeparator()
                                    Log.e("Resopncelogin5",otpResponse.data.loan_no)
                                    applicationno.text=otpResponse.data.application_no

                                    loanno.text=otpResponse.data.loan_no .replace("FTC","NFPL")
                                    val sansctiondata=otpResponse.data.loan_sanctioned.replace(".00","")
                                    loan_sanctioned.text=String.format("%,d",(sansctiondata.toLong()))+".00"

                                    val fee = otpResponse.data.processing_fee.replace(".00","")
                                    processing_fee.text=String.format("%,d",(fee.toLong()))+".00"
                                    tenur_roi.text = otpResponse.data.roi
                                    tenure_days.text = otpResponse.data.tenure
                                    val disbursal = otpResponse.data.net_disbursal_amount//.toString.replace(".00","")
                                    loan_disbursal.text=String.format("%,d",(disbursal.toLong()))+".00"
                                    disbursal_date.text = otpResponse.data.disbursal_date.substring(0,10)

                                    val format = SimpleDateFormat("dd-MMM-yyyy")
                                   // val date: String = format.format(Date.parse(otpResponse.data.repayment_date.substring(0,10)))
                                    var date1: Date?=null

                                    val date = otpResponse.data.repayment_date
                                    val input = SimpleDateFormat("yyyy-MM-dd")
                                    val output = SimpleDateFormat("dd-MM-yyyy")

                                    try {
                                        date1 = input.parse(date) // parse input
                                        Log.e("===============","======currentData======"+output.format(date1))


                                    } catch (e: ParseException) {
                                        e.printStackTrace()
                                    }





                                    repayment_date.text =output.format(date1)//otpResponse.data.repayment_date.substring(0,10)
                                    val repayment_am = otpResponse.data.payable_amount.toString().replace(".00","")
                                    repayements_amount.text=String.format("%,d",(repayment_am.toLong()))+".00"

                                    val payment_rc = otpResponse.data.payment_recived.replace(".00","")
                                    payment_received.text=String.format("%,d",(payment_rc.toLong()))+".00"

                                    val balance_d = otpResponse.data.balance_due.toString().replace(".00","")
                                    balance_due.text=String.format("%,d",(balance_d.toLong()))+".00"


                                    day_past_due.text = otpResponse.data.day_past_due.toString()

                                    panel_roi.text = otpResponse.data.penel_roi.replace("%","00")

                                    val intrest_day = otpResponse.data.penal_interest_per_day.toString().replace(".00","")
                                    panel_intrst_perday.text=String.format("%,d",(intrest_day.toLong()))+".00"

                                    inteset_payble.text = otpResponse.data.interest_payable.toString()

                                    val total_pay = otpResponse.data.total_payable.toString().replace(".00","")
                                    total_payble.text=String.format("%,d",(total_pay.toLong()))+".00"

                                    name_admine.text = otpResponse.data.name
                                    mobile_admin.text = "+91-"+otpResponse.data.mobile_no
                                    //loan_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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

    fun afterTextChanged(view: Editable) {
        var s: String? = null
        try {
            // The comma in the format specifier does the trick
            s = String.format("%,d", view.toString().toLong())
        } catch (e: NumberFormatException) {
        }
        // Set s back to the view after temporarily removing the text change listener
    }
    fun hideProgressBar() {
        loan_progress.visibility = View.GONE
    }
    fun showProgressBar() {
        loan_progress.visibility = View.VISIBLE
    }
}