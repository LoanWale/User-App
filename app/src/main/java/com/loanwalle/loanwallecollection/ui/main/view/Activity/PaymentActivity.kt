package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.checkpayment.CheckPaymentR_Request
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityPaymentBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.ChcekPayment_ADP
import com.loanwalle.loanwallecollection.ui.main.adapter.TodayLeadAdp
import com.loanwalle.loanwallecollection.ui.main.viewmodel.CheckPaymentViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import com.loanwalle.loanwallecollection.utils.toast
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_layout.*

class PaymentActivity : AppCompatActivity() {
     var bindig:ActivityPaymentBinding?=null
    lateinit var viewUpdatePaymetn: CheckPaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_payment)

        bindig= ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(bindig!!.root)


        init()

        back_layout_pay.setOnClickListener {
            paymenthistory_lnr.isVisible=false
            paymentmode_lner.isVisible=true
        }

        Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
        Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
      //  Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))

        Ra_online.setOnClickListener {
            Ra_web.visibility = View.VISIBLE
            Ra_qrcode.visibility = View.VISIBLE
          //  Ra_upi.visibility = View.VISIBLE
            Ra_Cash.visibility = View.GONE
        }

        Ra_offline.setOnClickListener {
            Ra_web.visibility = View.GONE
            Ra_qrcode.visibility = View.GONE
          //  Ra_upi.visibility = View.GONE
            Ra_Cash.visibility = View.VISIBLE
        }



        checkpayment_btn.setOnClickListener {
            CheckPaymentStatus()
        }



        paymenthistory_lnr.isVisible=false
        paymentmode_lner.isVisible=true



        submitpage.setOnClickListener {
            val intt= Intent(this,PaymentSuccessfullActivity::class.java)
             startActivity(intt)

        }


        Ra_web.setOnClickListener {
            Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
         //   Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))

        }

        Ra_qrcode.setOnClickListener{
            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.upi_id_drawable))
         //   Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))
            val intt= Intent(this,QrCodeActivity::class.java)
            startActivity(intt)
        }


//        Ra_upi.setOnClickListener{
//            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
//            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
//            Ra_upi.setBackground(getDrawable(R.drawable.upi_id_drawable))
//        }







    }


    private fun init() {
        rec_checkpayment.setHasFixedSize(true)
        rec_checkpayment.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }
    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewUpdatePaymetn = ViewModelProvider(this, factory).get(CheckPaymentViewModel::class.java)

    }
    fun CheckPaymentStatus() {
        val user = "44"// sessionManegar.getString(this, Constants.USER_ID)
        val userid = user
        if (userid!=null) {
            val body = CheckPaymentR_Request(
                "55546" )
            viewUpdatePaymetn.loginUser(body)
            Log.e("BODY",body.toString())
            viewUpdatePaymetn.loginResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Checkpayment",otpResponse.toString())
                                if (otpResponse.status.equals("200"))
                                {


                                    if (otpResponse.data.size==0)
                                    {
                                        paymenthistory_lnr.isVisible=false
                                        paymentmode_lner.isVisible=true

                                        toast("No Payment History found ")

                                    }else{
                                        paymenthistory_lnr.isVisible=true
                                        paymentmode_lner.isVisible=false
                                    }
                                    val status = otpResponse!!.data
                                    val picsAdapter = status?.let {
                                        ChcekPayment_ADP(this@PaymentActivity, it)
                                    }
                                    //progress4.errorSnack(message, Snackbar.LENGTH_LONG)
                                    rec_checkpayment.adapter = picsAdapter

                                }
                                else
                                {
                                    Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
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


    fun SubmitPage() {
        val user = "44"// sessionManegar.getString(this, Constants.USER_ID)
        val userid = user
        if (userid!=null) {
            val body = CheckPaymentR_Request(
                "55546" )
            viewUpdatePaymetn.loginUser(body)
            Log.e("BODY",body.toString())
            viewUpdatePaymetn.loginResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Checkpayment",otpResponse.toString())
                                if (otpResponse.status.equals("200"))
                                {


                                    // final submit



                                }
                                else
                                {
                                    Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
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
        Check_progr.visibility = View.GONE
    }
    fun showProgressBar() {
        Check_progr.visibility = View.VISIBLE
    }

}